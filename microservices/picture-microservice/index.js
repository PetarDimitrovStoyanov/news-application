require("dotenv/config");
const express = require("express");
const multer = require('multer');
const AWS = require('aws-sdk');
const KeyGenerator = require('uuid-key-generator');
const fs = require('fs');
const app = express();

const keygen = new KeyGenerator();
const uuid = new KeyGenerator(256, KeyGenerator.BASE62);

const port = 8005;
const s3 = new AWS.S3({
  accessKeyId: process.env.AWS_ID,
  secretAccessKey: process.env.AWS_SECRET
})

const storage = multer.memoryStorage({
  destination: function (req, file, callback) {
    callback(null, '')
  }
})

const upload = multer({storage}).single('image');
const uploadImage = multer({dest: "uploads/"})

const cors = require("cors");

app.use(express.urlencoded({extended: true}));
app.use(express.json());
app.use(cors());

app.get("/", cors(), async (req, res) => {
  res.send("get a picture endpoint - test")
});

function uploadPicture(file) {
  const fileStream = fs.createReadStream(file.path);
  let myFile = file.originalname.split(".");
  const fileType = myFile[myFile.length - 1];

  const uploadParams = {
    Bucket: process.env.AWS_BUCKET_NAME,
    Body: fileStream,
    Key: `${uuid.generateKey()}.${fileType}`
  }

  return s3.upload(uploadParams).promise();
}

app.post("/pictures/save-image", cors(), uploadImage.single('image'), async (req, res) => {

  const file = req.file;
  const result = await uploadPicture(file);
  const description = req.body.description;
  res.send(result);
});

/*app.post("/pictures/upload", cors(), upload, async (req, res) => {
  let myFile = req.file.originalname.split(".");
  const fileType = myFile[myFile.length - 1];

  const params = {
    Bucket: process.env.AWS_BUCKET_NAME,
    Key: `${uuid.generateKey()}.${fileType}`,
    Body: req.file.buffer
  }

  s3.upload(params, (error, data) => {
    if (error) {
      res.status(500).send(error);
    }

    res.status(200).send(data);
  });
});*/

function getFileStream(fileKey) {
  const downloadParams = {
    Key: fileKey,
    Bucket: process.env.AWS_BUCKET_NAME,
  }

  return s3.getObject(downloadParams).createReadStream();
}

app.get(`/pictures/get-a-picture/:key`, cors(), async (req, res) => {
  const key = req.params.key;
  const readStream = getFileStream(key);

  readStream.pipe(res);
});

app.delete("/pictures/delete/:key", cors(), async (req, res) => {
  const key = req.params.key;
  const params = {
    Bucket: process.env.AWS_BUCKET_NAME,
    Key: key
  }

  s3.deleteObject(params, (error, data) => {
    if (error) {
      res.status(500).send(error);
    }
    res.status(200).send(data);
  })

});

app.listen(port, () => {
  console.log(`The microservice is started on port: http://localhost:${port}`)
})
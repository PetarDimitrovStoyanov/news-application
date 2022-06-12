require("dotenv/config");
const express = require("express");
const multer = require('multer');
const AWS = require('aws-sdk');
const KeyGenerator = require('uuid-key-generator');
const fs = require('fs');
const app = express();

const uuid = new KeyGenerator(256, KeyGenerator.BASE62);

const port = 8005;
const s3 = new AWS.S3({
  accessKeyId: process.env.AWS_ID,
  secretAccessKey: process.env.AWS_SECRET
})

const uploadImage = multer({dest: "uploads/"})

const cors = require("cors");

const microserviceKeyInterceptor = function (req, res, next) {
  let key = req.headers.key.split(", ").shift();
  if (key !== process.env.MICROSERVICE_KEY) {
	throw new Error('The microservice key is missing.')
  }
  next();
};

app.use(express.urlencoded({extended: true}));
app.use(express.json());
app.use(cors());

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

app.post("/pictures/save-image", cors(), microserviceKeyInterceptor, uploadImage.single('image'), async (req, res) => {

  const file = req.file;
  const result = await uploadPicture(file);
  const description = req.body.description;
  res.send(result);
});

function getFileStream(fileKey) {
  const downloadParams = {
	Key: fileKey,
	Bucket: process.env.AWS_BUCKET_NAME,
  }

  return s3.getObject(downloadParams).createReadStream();
}

app.get(`/pictures/get-a-picture/:key`, cors(), microserviceKeyInterceptor, async (req, res) => {
  const key = req.params.key;
  const readStream = getFileStream(key);

  readStream.pipe(res);
});

app.delete("/pictures/delete/:key", cors(), microserviceKeyInterceptor,  async (req, res) => {
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
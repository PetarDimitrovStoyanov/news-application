package com.newsAapplicationMicroservice.authmicroservice.service;

import com.newsAapplicationMicroservice.authmicroservice.dto.DeleteRequestMainMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.DeleteRequestNewsMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.EditRequestMainMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.EditRequestNewsMicroserviceDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.StatusRequestMainDTO;
import com.newsAapplicationMicroservice.authmicroservice.dto.StatusRequestNewsDTO;

public interface UserEntityService {
    EditRequestNewsMicroserviceDTO convertEditDTO(EditRequestMainMicroserviceDTO editRequestMainMicroserviceDTO);

    DeleteRequestNewsMicroserviceDTO convertDeleteDTO(DeleteRequestMainMicroserviceDTO mainDTO);

    StatusRequestNewsDTO convertStatusDTO(StatusRequestMainDTO mainDTO);
}
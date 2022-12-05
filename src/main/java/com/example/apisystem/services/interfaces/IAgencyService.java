package com.example.apisystem.services.interfaces;

import com.example.apisystem.controllers.dtos.requests.CreateAgencyRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.entities.Agency;

public interface IAgencyService {
    BaseResponse list();
    BaseResponse create(CreateAgencyRequest request);

    Agency findById(Long id);
}

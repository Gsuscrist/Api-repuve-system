package com.example.apisystem.services.interfaces;

import com.example.apisystem.controllers.dtos.requests.CreateWorkerRequest;
import com.example.apisystem.controllers.dtos.requests.UpdateWorkerRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.entities.Worker;

public interface IWorkerService {

BaseResponse getById(Long id);
BaseResponse create(CreateWorkerRequest request);
BaseResponse update(Long id, UpdateWorkerRequest request);

Worker findById(Long id);
void save(Worker worker);

BaseResponse signIn(String email, String password);
}

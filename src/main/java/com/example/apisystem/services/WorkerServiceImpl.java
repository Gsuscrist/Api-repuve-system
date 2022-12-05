package com.example.apisystem.services;

import com.example.apisystem.controllers.dtos.requests.CreateWorkerRequest;
import com.example.apisystem.controllers.dtos.requests.UpdateWorkerRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.controllers.dtos.responses.GetWorkerResponse;
import com.example.apisystem.entities.Worker;
import com.example.apisystem.repositories.IWorkerRepository;
import com.example.apisystem.services.interfaces.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WorkerServiceImpl implements IWorkerService {
    @Autowired
    private IWorkerRepository repository;

    @Override
    public BaseResponse getById(Long id) {
        GetWorkerResponse response = from(id);

        return BaseResponse.builder()
                .data(response)
                .message("Worker Data")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateWorkerRequest request) {
        GetWorkerResponse response = from(repository.save(from(request)));
        return BaseResponse.builder()
                .data(response)
                .message("Worker Creation")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(Long id, UpdateWorkerRequest request) {
        Worker worker=repository.findById(id).orElseThrow(()->new RuntimeException("Worker do not exist"));
        worker = update(worker, request);
        GetWorkerResponse response=from(worker);

        return BaseResponse.builder()
                .data(response)
                .message("Worker Data Update")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void save(Worker worker) {
        repository.save(worker);
    }

    public Worker findById(Long id){
        Worker worker=repository.findById(id).orElseThrow(()->new RuntimeException("Worker do not Exist"));
        return worker;
    }

    @Override
    public BaseResponse signIn(String email, String password) {
        GetWorkerResponse response = from(email, password);

        return BaseResponse.builder()
                .data(response)
                .message("Worker Sign In")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private GetWorkerResponse from(String email, String password){
        Worker worker= repository.workerSignIn(email, password);
        GetWorkerResponse response =from(worker);
        return response;
    }

    private Worker update(Worker worker, UpdateWorkerRequest request){
        worker.setProfilePicture(request.getProfilePicture());
        return repository.save(worker);
    }

    private Worker from(CreateWorkerRequest request){
        Worker worker = new Worker();
        worker.setName(request.getName());
        worker.setLastName(request.getLastName());
        worker.setEmail(request.getEmail());
        worker.setPassword(request.getPassword());
        worker.setWorkerCode(request.getWorkerCode());
        return worker;
    }

    private GetWorkerResponse from(Worker worker){
        GetWorkerResponse response =new GetWorkerResponse();
      response.setId(worker.getId());
      response.setName(worker.getName());
      response.setLastName(worker.getLastName());
      response.setProfilePicture(worker.getProfilePicture());
        return response;
    }

    private GetWorkerResponse from(Long id){
        return repository.findById(id).map(this::from).orElseThrow(()->new RuntimeException("Worker do not exist"));
    }
}

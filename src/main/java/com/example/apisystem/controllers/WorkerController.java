package com.example.apisystem.controllers;

import com.example.apisystem.controllers.dtos.requests.CreateWorkerRequest;
import com.example.apisystem.controllers.dtos.requests.SignInWorkerRequest;
import com.example.apisystem.controllers.dtos.requests.UpdateWorkerRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.services.interfaces.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("worker")
public class WorkerController {
    @Autowired
    private IWorkerService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getIn(@PathVariable Long id){
        BaseResponse baseResponse= service.getById(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
    @PostMapping("signIn")
    public ResponseEntity<BaseResponse> signIn(@RequestBody SignInWorkerRequest request){
        BaseResponse baseResponse= service.signIn(request.getEmail(),request.getPassword());
        System.out.println(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateWorkerRequest request){
        BaseResponse baseResponse=service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateWorkerRequest request){
        BaseResponse baseResponse=service.update(id,request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}

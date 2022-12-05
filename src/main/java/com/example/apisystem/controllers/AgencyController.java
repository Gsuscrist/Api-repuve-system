package com.example.apisystem.controllers;

import com.example.apisystem.controllers.dtos.requests.CreateAgencyRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.services.interfaces.IAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("agency")
public class AgencyController {
    @Autowired
    private IAgencyService service;

    @GetMapping
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse=service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateAgencyRequest request){
        BaseResponse baseResponse=service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
}

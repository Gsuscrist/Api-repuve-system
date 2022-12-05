package com.example.apisystem.controllers;

import com.example.apisystem.controllers.dtos.requests.CreateVehicleRequest;
import com.example.apisystem.controllers.dtos.requests.UpdateVehicleRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.controllers.dtos.responses.GetVehicleResponse;
import com.example.apisystem.services.interfaces.IVehicleService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private IVehicleService service;


    @GetMapping("/id/{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable Long id){
        BaseResponse baseResponse=service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());

    }

    @GetMapping("{niv}")
    public ResponseEntity<BaseResponse> search(@PathVariable String niv){
        BaseResponse baseResponse = service.getVehicleByNiv(niv);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("/page/{pag}")
    public Page<GetVehicleResponse> page(@PathVariable Integer pag){
        Page<GetVehicleResponse> page =service.findAll(PageRequest.of(pag,20));
        return page;
    }


    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateVehicleRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody UpdateVehicleRequest request){
        BaseResponse baseResponse = service.update(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}

package com.example.apisystem.services.interfaces;

import com.example.apisystem.controllers.dtos.requests.CreateVehicleRequest;
import com.example.apisystem.controllers.dtos.requests.UpdateVehicleRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.controllers.dtos.responses.GetVehicleResponse;
import com.example.apisystem.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IVehicleService {

    BaseResponse getVehicleByNiv(String niv);

    BaseResponse get(Long id);

    BaseResponse create(CreateVehicleRequest request);

    BaseResponse update(Long id, UpdateVehicleRequest request);

    Page<GetVehicleResponse> findAll(Pageable pageable);

    Vehicle getById(Long id);

    void save(Vehicle vehicle);

}

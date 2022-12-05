package com.example.apisystem.services;

import com.example.apisystem.controllers.dtos.requests.CreateVehicleRequest;
import com.example.apisystem.controllers.dtos.requests.UpdateVehicleRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.controllers.dtos.responses.GetVehicleResponse;
import com.example.apisystem.entities.Agency;
import com.example.apisystem.entities.Vehicle;
import com.example.apisystem.repositories.IVehicleRepository;
import com.example.apisystem.services.interfaces.IAgencyService;
import com.example.apisystem.services.interfaces.IFileService;
import com.example.apisystem.services.interfaces.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VehicleServiceImpl implements IVehicleService {
    @Autowired
    private IVehicleRepository repository;

    @Autowired
    private IAgencyService agencyService;


    @Override
    public BaseResponse get(Long id) {
        GetVehicleResponse response = from(id);
        return BaseResponse.builder()
                .data(response)
                .message("vehicle by ID")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();

    }

    @Override
    public BaseResponse getVehicleByNiv(String niv) {
        GetVehicleResponse response= from(niv);

        return BaseResponse.builder()
                .data(response)
                .message("vehicle by NIV")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateVehicleRequest request) {
        GetVehicleResponse response=from(repository.save(from(request)));

        return BaseResponse.builder()
                .data(response)
                .message("vehicle Creation")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();    }

    @Override
    public BaseResponse update(Long id, UpdateVehicleRequest request) {
       Vehicle vehicle =repository.findById(id).orElseThrow(()->new RuntimeException("Vehicle Not Found"));
       vehicle= update(vehicle, request);
       GetVehicleResponse response= from(vehicle);

        return BaseResponse.builder()
                .data(response)
                .message("vehicle Updated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Page<GetVehicleResponse> findAll(Pageable pageable) {
        Page<GetVehicleResponse> response =repository.findAll(pageable).map(this::from);

        return response;
    }

    @Override
    public Vehicle getById(Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("Vehicle do not exist"));
    }

    @Override
    public void save(Vehicle vehicle) {
    repository.save(vehicle);
    }

    private Vehicle update(Vehicle vehicle, UpdateVehicleRequest request){
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setColor(request.getColor());
        vehicle.setEngineType(request.getEngineType());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setPlateNumber(request.getPlateNumber());
        vehicle.setVehicleNumberIdentification(request.getVehicleNumberId());
        vehicle.setOwnerName(request.getOwnerName());
        vehicle.setOwnerLastName(request.getOwnerLastName());
//        vehicle.setVehiclePicture(fileService.uploadVehiclePic(image));
        vehicle.setPrice(request.getPrice());
        Agency agency= agencyService.findById(request.getAgencyId());
        vehicle.setAgency(agency);
        return repository.save(vehicle);
    }
    private Vehicle from (CreateVehicleRequest request){
        Vehicle vehicle=new Vehicle();
        vehicle.setBrand(request.getBrand());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setColor(request.getColor());
        vehicle.setEngineType(request.getEngineType());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setPlateNumber(request.getPlateNumber());
        vehicle.setVehicleNumberIdentification(request.getVehicleNumberId());
        vehicle.setOwnerName(request.getOwnerName());
        vehicle.setOwnerLastName(request.getOwnerLastName());
//        vehicle.setVehiclePicture(fileService.uploadVehiclePic(imagen));
        vehicle.setPrice(request.getPrice());
        Agency agency= agencyService.findById(request.getAgencyId());
        vehicle.setAgency(agency);
        return vehicle;
    }

    private GetVehicleResponse from(Vehicle vehicle){
        GetVehicleResponse response = new GetVehicleResponse();
        response.setId(vehicle.getId());
        response.setBrand(vehicle.getBrand());
        response.setModel(vehicle.getModel());
        response.setYear(vehicle.getYear());
        response.setColor(vehicle.getColor());
        response.setEngineType(vehicle.getEngineType());
        response.setFuelType(vehicle.getFuelType());
        response.setPlateNumber(vehicle.getPlateNumber());
        response.setVehicleNumberId(vehicle.getVehicleNumberIdentification());
        response.setOwnerName(vehicle.getOwnerName());
        response.setOwnerLastName(vehicle.getOwnerLastName());
        response.setVehiclePicture(vehicle.getVehiclePicture());
        response.setPrice(vehicle.getPrice());
        Agency agency= vehicle.getAgency();
        response.setAgencyId(agency.getId());
        response.setAgencyName(agency.getName());


        return response;
    }

    private GetVehicleResponse from(Long id){
        Vehicle vehicle = repository.findById(id).orElseThrow(()->new RuntimeException("Vehicle do not exist"));
        GetVehicleResponse response = from(vehicle);
        return response;

    }
    private GetVehicleResponse from(String niv){
        Vehicle vehicle = repository.getVehicleByNiv(niv);
        GetVehicleResponse response = from(vehicle);
        return response;

    }
}

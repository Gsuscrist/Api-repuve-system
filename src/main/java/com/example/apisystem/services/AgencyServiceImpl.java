package com.example.apisystem.services;

import com.example.apisystem.controllers.dtos.requests.CreateAgencyRequest;
import com.example.apisystem.controllers.dtos.responses.BaseResponse;
import com.example.apisystem.controllers.dtos.responses.GetAgencyResponse;
import com.example.apisystem.entities.Agency;
import com.example.apisystem.repositories.IAgencyRepository;
import com.example.apisystem.services.interfaces.IAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgencyServiceImpl implements IAgencyService {
    @Autowired
    private IAgencyRepository repository;

    @Override
    public BaseResponse list() {
       List<GetAgencyResponse> response = repository.findAll()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .data(response)
                .message("Agencies list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateAgencyRequest request) {
       GetAgencyResponse response= from(repository.save(from(request)));
        return BaseResponse.builder()
                .data(response)
                .message("Agency Creation")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Agency findById(Long id) {
        return repository.findById(id).orElseThrow(()->new RuntimeException("agency do not exist"));
    }

    private Agency from(CreateAgencyRequest request){
        Agency agency =new Agency();
        agency.setName(request.getName());
        agency.setAddress(request.getAddress());
        agency.setCity(request.getCity());
        agency.setState(request.getState());
        agency.setCountry(request.getCountry());
        agency.setPhoneNumber(request.getPhoneNumber());

        return agency;
    }

    private GetAgencyResponse from(Agency agency){
        GetAgencyResponse response = new GetAgencyResponse();
        response.setId(agency.getId());
        response.setName(agency.getName());
        response.setAddress(agency.getAddress());
        response.setCity(agency.getCity());
        response.setState(agency.getState());
        response.setCountry(agency.getCountry());
        response.setPhoneNumber(agency.getPhoneNumber());
        return response;
    }
}

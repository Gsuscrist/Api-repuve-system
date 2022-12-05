package com.example.apisystem.controllers.dtos.responses;

import com.example.apisystem.entities.Agency;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetVehicleResponse {

    private Long id;

    private String brand;

    private String model;

    private Integer year;

    private String color;

    private String engineType;

    private String fuelType;

    private String plateNumber;

    private String vehicleNumberId;

    private String ownerName;

    private String ownerLastName;

    private String vehiclePicture;

    private Double price;

    private String agencyName;

    private Long agencyId;
}

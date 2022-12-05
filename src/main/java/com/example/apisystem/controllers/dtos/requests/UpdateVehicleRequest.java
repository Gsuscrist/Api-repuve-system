package com.example.apisystem.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateVehicleRequest {

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

    private Long agencyId;

    private Double price;
}

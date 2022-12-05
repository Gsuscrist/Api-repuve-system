package com.example.apisystem.controllers.dtos.responses;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAgencyResponse {

    private Long id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String country;

    private String phoneNumber;
}

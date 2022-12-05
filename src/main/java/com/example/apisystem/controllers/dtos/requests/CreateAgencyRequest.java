package com.example.apisystem.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAgencyRequest {

    private String name;

    private String address;

    private String city;

    private String state;

    private String country;

    private String phoneNumber;
}

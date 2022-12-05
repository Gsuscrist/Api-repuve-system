package com.example.apisystem.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateWorkerRequest {
    private String name;

    private String lastName;

    private  String email;

    private String password;

    private Long workerCode;

    private String profilePicture;
}

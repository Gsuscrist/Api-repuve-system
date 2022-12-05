package com.example.apisystem.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetWorkerResponse {

    private Long id;

    private String name;

    private String lastName;

    private String profilePicture;
}

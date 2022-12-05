package com.example.apisystem.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInWorkerRequest {
    private String email;
    private String password;

    @Override
    public String toString() {
        return "SignInWorkerRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.example.apisystem.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String engineType;

    @Column(nullable = false)
    private String fuelType;

    @Column(nullable = false)
    private String plateNumber;

    @Column(nullable = false)
    private String vehicleNumberIdentification;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private String ownerLastName;

    private String vehiclePicture;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Agency agency;

}

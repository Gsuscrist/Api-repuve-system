package com.example.apisystem.repositories;

import com.example.apisystem.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT v.* FROM vehicles v WHERE v.vehicle_number_identification= :niv", nativeQuery = true)
    Vehicle getVehicleByNiv(String niv);




}

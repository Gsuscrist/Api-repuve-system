package com.example.apisystem.repositories;

import com.example.apisystem.entities.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgencyRepository extends JpaRepository<Agency, Long> {
}

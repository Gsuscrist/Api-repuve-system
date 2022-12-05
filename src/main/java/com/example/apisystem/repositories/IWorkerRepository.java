package com.example.apisystem.repositories;

import com.example.apisystem.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value = "select w.* from  workers  w where w.email=:email and w.password=:password", nativeQuery = true)
    Worker workerSignIn(String email, String password);

}

package com.example.apisystem.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String uploadVehiclePic(MultipartFile multipartFile, Long idVehicle);
    String uploadWorkerPic(MultipartFile multipartFile, Long idWorker);
}

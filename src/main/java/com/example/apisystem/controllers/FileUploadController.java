package com.example.apisystem.controllers;

import com.example.apisystem.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("file")
public class FileUploadController {
    @Autowired
    private IFileService service;

    @PostMapping("/vehicle")
    public String uploadVehiclePic(@RequestParam MultipartFile image, @RequestParam Long idVehicle){
        return service.uploadVehiclePic(image, idVehicle);
    }

    @PostMapping("/worker")
    public String uploadWorkerPic(@RequestParam MultipartFile image, @RequestParam Long idWorker){
        return service.uploadWorkerPic(image,idWorker);
    }

}

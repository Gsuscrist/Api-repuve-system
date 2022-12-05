package com.example.apisystem.services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.apisystem.entities.Vehicle;
import com.example.apisystem.entities.Worker;
import com.example.apisystem.services.interfaces.IFileService;
import com.example.apisystem.services.interfaces.IVehicleService;
import com.example.apisystem.services.interfaces.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IWorkerService workerService;

    private AmazonS3 s3client;

    private String ENDPOINT_URL = "s3.amazonaws.com";

    private String BUCKET_NAME = "repuve";

    private String ACCESS_KEY = "AKIAVZNHG2B5Y2IVZD4M";

    private String SECRET_KEY = "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";


    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileToS3Bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String uploadVehiclePic(MultipartFile multipartFile, Long idVehicle) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/"+ fileName;
            uploadFileToS3Bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Vehicle vehicle= vehicleService.getById(idVehicle);
        vehicle.setVehiclePicture(fileUrl);
        vehicleService.save(vehicle);
        return fileUrl;
    }

    @Override
    public String uploadWorkerPic(MultipartFile multipartFile, Long idWorker) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            fileUrl = "https://" + BUCKET_NAME + "." + ENDPOINT_URL + "/"+ fileName;
            uploadFileToS3Bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Worker worker= workerService.findById(idWorker);
        worker.setProfilePicture(fileUrl);
        workerService.save(worker);
        return fileUrl;
    }

    //    public String deleteFileFromS3Bucket(String fileUrl) {
//        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//        s3client.deleteObject(new DeleteObjectRequest(BUCKET_NAME, fileName));
//        return "Successfully deleted";
//    }

}

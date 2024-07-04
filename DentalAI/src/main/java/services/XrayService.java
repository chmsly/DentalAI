package com.example.dentalxray.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.dentalxray.model.XrayImage;
import com.example.dentalxray.repository.XrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class XrayService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private XrayRepository xrayRepository;

    public String uploadXray(MultipartFile file, String userId) throws IOException {
        String bucketName = "your-bucket-name";
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), new ObjectMetadata()));
        String s3Url = amazonS3.getUrl(bucketName, fileName).toString();


        return s3Url;
    }
    public void saveXray(XrayImage xrayImage) {
        xrayRepository.save(xrayImage);
    }
    public XrayImage findById(String id) {
        return xrayRepository.findById(id).orElse(null);
    }
}

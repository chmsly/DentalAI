package com.example.dentalxray.controller;

import com.example.dentalxray.model.AnalysisResult;
import com.example.dentalxray.service.AIAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/xray-analysis")
public class XrayAnalysisController {

    @Autowired
    private AIAnalysisService aiAnalysisService;

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResult> analyzeXray(
            @RequestParam("xrayImage") MultipartFile xrayImage,
            @RequestParam("dentistDiagnosis") String dentistDiagnosis,
            @RequestParam("userId") String userId) {
        try {
            AnalysisResult result = aiAnalysisService.analyzeXray(xrayImage, dentistDiagnosis, userId);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
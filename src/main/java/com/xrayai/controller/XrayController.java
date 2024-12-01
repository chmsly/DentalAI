package com.xrayai.controller;

import com.xrayai.model.AnalysisResult;
import com.xrayai.service.XrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/xrays")
public class XrayController {

    private final XrayService xrayService;

    @Autowired
    public XrayController(XrayService xrayService) {
        this.xrayService = xrayService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResult> analyzeXray(
            @RequestParam("file") MultipartFile file,
            @RequestParam("diagnosis") String diagnosis,
            @RequestParam("userId") String userId) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            // Validate file type
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().build();
            }

            AnalysisResult result = xrayService.analyzeXray(file, diagnosis, userId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
} 
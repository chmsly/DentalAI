package com.dentalai.controllers;

import com.dentalai.services.AIAnalysisService;
import com.dentalai.model.AnalysisResult;
import com.dentalai.exception.AnalysisException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/xray-analysis")
public class XrayAnalysisController {

    private final AIAnalysisService aiAnalysisService;

    public XrayAnalysisController(AIAnalysisService aiAnalysisService) {
        this.aiAnalysisService = aiAnalysisService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<AnalysisResult> analyzeXray(
            @RequestParam("file") MultipartFile file,
            @RequestParam("dentistDiagnosis") String dentistDiagnosis,
            @RequestParam("userId") String userId) throws AnalysisException {
        AnalysisResult result = aiAnalysisService.analyzeXray(file, dentistDiagnosis, userId);
        return ResponseEntity.ok(result);
    }
}

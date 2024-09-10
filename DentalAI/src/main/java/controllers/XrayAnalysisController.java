package com.example.dentalxray.controller;

import com.example.dentalxray.model.AnalysisResult;
import com.example.dentalxray.service.AIAnalysisService;
import com.example.dentalxray.exception.InvalidInputException;
import com.example.dentalxray.exception.AnalysisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/xray-analysis")
@Validated
public class XrayAnalysisController {

    @Autowired
    private AIAnalysisService aiAnalysisService;

    @PostMapping("/analyze")
    @PreAuthorize("hasRole('DENTIST')")
    public ResponseEntity<?> analyzeXray(
            @RequestParam("xrayImage") @NotNull MultipartFile xrayImage,
            @RequestParam("dentistDiagnosis") @NotBlank String dentistDiagnosis,
            @RequestParam("userId") @NotBlank String userId) {
        try {
            validateXrayImage(xrayImage);
            AnalysisResult result = aiAnalysisService.analyzeXray(xrayImage, dentistDiagnosis, userId);
            return ResponseEntity.ok(result);
        } catch (InvalidInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AnalysisException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private void validateXrayImage(MultipartFile file) throws InvalidInputException {
        if (file.isEmpty()) {
            throw new InvalidInputException("X-ray image file is empty");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new InvalidInputException("Uploaded file is not an image");
        }
    }
}
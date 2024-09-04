package com.example.dentalxray.service;

import com.example.dentalxray.model.*;
import com.example.dentalxray.repository.AnalysisReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AIAnalysisService {

    @Autowired
    private AIModelService aiModelService;

    @Autowired
    private AnalysisReportRepository analysisReportRepository;

    public AnalysisResult analyzeXray(MultipartFile xrayImage, String dentistDiagnosis, String userId) throws IOException {
        BufferedImage processedImage = preprocessImage(xrayImage);
        AIModelResult aiResult = runAIModel(processedImage);
        ComparisonResult comparison = compareResults(aiResult, dentistDiagnosis);
        AnalysisReport report = generateReport(comparison, userId, xrayImage.getOriginalFilename());
        saveAnalysisResult(report);
        return new AnalysisResult(report, generateHeatmap(processedImage, aiResult));
    }

    private BufferedImage preprocessImage(MultipartFile xrayImage) throws IOException {
        BufferedImage originalImage = ImageIO.read(xrayImage.getInputStream());
        // Apply preprocessing techniques (e.g., noise reduction, contrast enhancement)
        // For simplicity, we're returning the original image
        return originalImage;
    }

    private AIModelResult runAIModel(BufferedImage processedImage) {
        return aiModelService.analyzeImage(processedImage);
    }

    private ComparisonResult compareResults(AIModelResult aiResult, String dentistDiagnosis) {
        List<Anomaly> dentistAnomalies = parseDentistDiagnosis(dentistDiagnosis);
        List<Anomaly> missedAnomalies = findMissedAnomalies(aiResult.getDetectedAnomalies(), dentistAnomalies);
        double agreementScore = calculateAgreementScore(aiResult.getDetectedAnomalies(), dentistAnomalies);
        
        return new ComparisonResult(aiResult.getDetectedAnomalies(), dentistAnomalies, missedAnomalies, agreementScore);
    }

    private List<Anomaly> parseDentistDiagnosis(String dentistDiagnosis) {
        // Parse the dentist's diagnosis string and convert it to a list of Anomaly objects
        // This is a placeholder implementation
        return List.of();
    }

    private List<Anomaly> findMissedAnomalies(List<Anomaly> aiAnomalies, List<Anomaly> dentistAnomalies) {
        // Compare AI-detected anomalies with dentist-detected anomalies and return the missed ones
        // This is a placeholder implementation
        return List.of();
    }

    private double calculateAgreementScore(List<Anomaly> aiAnomalies, List<Anomaly> dentistAnomalies) {
        // Calculate a score representing the agreement between AI and dentist diagnoses
        // This is a placeholder implementation
        return 0.0;
    }

    private AnalysisReport generateReport(ComparisonResult comparison, String userId, String xrayFileName) {
        String recommendations = generateRecommendations(comparison);
        return new AnalysisReport(
            UUID.randomUUID().toString(),
            xrayFileName,
            userId,
            comparison,
            LocalDateTime.now(),
            recommendations
        );
    }

    private String generateRecommendations(ComparisonResult comparison) {
        // Generate recommendations based on the comparison result
        // This is a placeholder implementation
        return "Please review the missed anomalies highlighted in the report.";
    }

    private void saveAnalysisResult(AnalysisReport report) {
        analysisReportRepository.save(report);
    }

    private BufferedImage generateHeatmap(BufferedImage originalImage, AIModelResult aiResult) {
        // Generate a heatmap overlay based on the AI model results
        // This is a placeholder implementation
        return originalImage;
    }
}
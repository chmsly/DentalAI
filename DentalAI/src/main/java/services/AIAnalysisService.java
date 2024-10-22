package com.dentalai.services;

import com.dentalai.model.AIModelResult;
import com.dentalai.model.AnalysisReport;
import com.dentalai.model.AnalysisResult;
import com.dentalai.model.Anomaly;
import com.dentalai.model.ComparisonResult;
import com.dentalai.repository.AnalysisReportRepository;
import com.dentalai.exception.AnalysisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AIAnalysisService {

    @Autowired
    private AIModelService aiModelService;

    @Autowired
    private AnalysisReportRepository analysisReportRepository;

    public AnalysisResult analyzeXray(MultipartFile xrayImage, String dentistDiagnosis, String userId) throws AnalysisException {
        try {
            BufferedImage processedImage = preprocessImage(xrayImage);
            AIModelResult aiResult = runAIModel(processedImage);
            ComparisonResult comparison = compareResults(aiResult, dentistDiagnosis);
            AnalysisReport report = generateReport(comparison, userId, xrayImage.getOriginalFilename());
            saveAnalysisResult(report);
            return new AnalysisResult(report, generateHeatmap(processedImage, aiResult));
        } catch (IOException e) {
            throw new AnalysisException("Error processing X-ray image", e);
        } catch (Exception e) {
            throw new AnalysisException("Error during X-ray analysis", e);
        }
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
        // This is a simplified implementation
        return List.of(new Anomaly("Sample Anomaly", "Sample Location"));
    }

    private List<Anomaly> findMissedAnomalies(List<Anomaly> aiAnomalies, List<Anomaly> dentistAnomalies) {
        return dentistAnomalies.stream()
                .filter(dentistAnomaly -> !aiAnomalies.contains(dentistAnomaly))
                .collect(Collectors.toList());
    }

    private double calculateAgreementScore(List<Anomaly> aiAnomalies, List<Anomaly> dentistAnomalies) {
        int matchingAnomalies = (int) aiAnomalies.stream()
                .filter(dentistAnomalies::contains)
                .count();
        return (double) matchingAnomalies / Math.max(aiAnomalies.size(), dentistAnomalies.size());
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
        StringBuilder recommendations = new StringBuilder();
        if (comparison.getMissedAnomalies().isEmpty()) {
            recommendations.append("AI analysis aligns well with the dentist's diagnosis. ");
        } else {
            recommendations.append("Please review the following missed anomalies: ");
            comparison.getMissedAnomalies().forEach(anomaly -> 
                recommendations.append(anomaly.getDescription()).append(" at ").append(anomaly.getLocation()).append(". "));
        }
        recommendations.append("Agreement score: ").append(String.format("%.2f", comparison.getAgreementScore()));
        return recommendations.toString();
    }

    private void saveAnalysisResult(AnalysisReport report) throws AnalysisException {
        try {
            analysisReportRepository.save(report);
        } catch (Exception e) {
            throw new AnalysisException("Error saving analysis report", e);
        }
    }

    private BufferedImage generateHeatmap(BufferedImage processedImage, AIModelResult aiResult) {
        // Implement heatmap generation logic
        // This is a placeholder implementation
        return processedImage;
    }
}

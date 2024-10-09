package com.dentalai.service;

import com.dentalai.model.*;
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

    // Implement other methods (preprocessImage, runAIModel, compareResults, generateReport, generateHeatmap)
    // ...

    private void saveAnalysisResult(AnalysisReport report) throws AnalysisException {
        try {
            analysisReportRepository.save(report);
        } catch (Exception e) {
            throw new AnalysisException("Error saving analysis report", e);
        }
    }

    private double calculateAgreementScore(List<Anomaly> aiAnomalies, List<Anomaly> dentistAnomalies) {
        int matchingAnomalies = 0;
        for (Anomaly aiAnomaly : aiAnomalies) {
            if (dentistAnomalies.contains(aiAnomaly)) {
                matchingAnomalies++;
            }
        }
        return (double) matchingAnomalies / Math.max(aiAnomalies.size(), dentistAnomalies.size());
    }

    private String generateRecommendations(ComparisonResult comparison) {
        StringBuilder recommendations = new StringBuilder();
        if (comparison.getMissedAnomalies().isEmpty() && comparison.getFalsePositives().isEmpty()) {
            recommendations.append("AI and dentist diagnoses align well. No specific recommendations.");
        } else {
            if (!comparison.getMissedAnomalies().isEmpty()) {
                recommendations.append("Please review these missed anomalies: ")
                               .append(String.join(", ", comparison.getMissedAnomalies()))
                               .append(". ");
            }
            if (!comparison.getFalsePositives().isEmpty()) {
                recommendations.append("The AI may have incorrectly identified these as anomalies: ")
                               .append(String.join(", ", comparison.getFalsePositives()))
                               .append(". ");
            }
        }
        return recommendations.toString();
    }
}
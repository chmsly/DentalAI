package com.example.dentalxray.service;

import com.example.dentalxray.model.*;
import com.example.dentalxray.repository.AnalysisReportRepository;
import com.example.dentalxray.exception.AnalysisException;
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

    private BufferedImage preprocessImage(MultipartFile xrayImage) throws IOException {
        return ImageIO.read(xrayImage.getInputStream());
    }

    private AIModelResult runAIModel(BufferedImage processedImage) {
        return aiModelService.analyzeImage(processedImage);
    }

    private ComparisonResult compareResults(AIModelResult aiResult, String dentistDiagnosis) {
        // Implementation details...
        return new ComparisonResult(/* ... */);
    }

    private AnalysisReport generateReport(ComparisonResult comparison, String userId, String originalFilename) {
        // Implementation details...
        return new AnalysisReport(/* ... */);
    }

    private void saveAnalysisResult(AnalysisReport report) throws AnalysisException {
        try {
            analysisReportRepository.save(report);
        } catch (Exception e) {
            throw new AnalysisException("Error saving analysis report", e);
        }
    }

    private BufferedImage generateHeatmap(BufferedImage originalImage, AIModelResult aiResult) {
        // Implementation details...
        return originalImage;
    }
}
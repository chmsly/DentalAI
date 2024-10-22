package com.dentalai.services;

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
public class XrayService {

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

    // ... (other methods remain the same)

    private void saveAnalysisResult(AnalysisReport report) throws AnalysisException {
        try {
            analysisReportRepository.save(report);
        } catch (Exception e) {
            throw new AnalysisException("Error saving analysis report", e);
        }
    }
}

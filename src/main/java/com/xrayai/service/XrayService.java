package com.xrayai.service;

import com.xrayai.model.Anomaly;
import com.xrayai.model.AnalysisResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class XrayService {

    private final AIModelService aiModelService;
    private final Random random = new Random();

    @Autowired
    public XrayService(AIModelService aiModelService) {
        this.aiModelService = aiModelService;
    }

    public AnalysisResult analyzeXray(MultipartFile file, String diagnosis, String userId) {
        // Get AI analysis
        List<Anomaly> aiAnomalies = aiModelService.detectAnomalies(file);

        // Parse dentist's diagnosis into anomalies (simplified for demo)
        List<Anomaly> dentistAnomalies = parseDiagnosis(diagnosis);

        // Calculate agreement score (simplified for demo)
        double agreementScore = calculateAgreementScore(aiAnomalies, dentistAnomalies);

        // Generate recommendations based on findings
        String recommendations = generateRecommendations(aiAnomalies, dentistAnomalies);

        return new AnalysisResult(
            aiAnomalies,
            dentistAnomalies,
            agreementScore,
            recommendations
        );
    }

    private List<Anomaly> parseDiagnosis(String diagnosis) {
        // Simplified parsing for demo - creates 1-3 anomalies from the diagnosis
        List<Anomaly> anomalies = new ArrayList<>();
        String[] commonTypes = {"cavity", "fracture", "infection", "abscess"};
        String[] locations = {"upper left", "upper right", "lower left", "lower right"};
        
        int numAnomalies = random.nextInt(3) + 1;
        for (int i = 0; i < numAnomalies; i++) {
            anomalies.add(new Anomaly(
                commonTypes[random.nextInt(commonTypes.length)],
                locations[random.nextInt(locations.length)],
                0.7 + random.nextDouble() * 0.3 // Random confidence between 0.7 and 1.0
            ));
        }
        return anomalies;
    }

    private double calculateAgreementScore(List<Anomaly> aiAnomalies, List<Anomaly> dentistAnomalies) {
        // Simplified scoring for demo
        if (aiAnomalies.isEmpty() && dentistAnomalies.isEmpty()) return 1.0;
        if (aiAnomalies.isEmpty() || dentistAnomalies.isEmpty()) return 0.0;
        
        return 0.4 + random.nextDouble() * 0.6; // Random score between 0.4 and 1.0
    }

    private String generateRecommendations(List<Anomaly> aiAnomalies, List<Anomaly> dentistAnomalies) {
        StringBuilder recommendations = new StringBuilder();
        recommendations.append("Based on the analysis:\n\n");

        if (aiAnomalies.size() > dentistAnomalies.size()) {
            recommendations.append("- AI has detected additional anomalies that may require attention.\n");
        } else if (dentistAnomalies.size() > aiAnomalies.size()) {
            recommendations.append("- Some findings in the diagnosis may need additional verification.\n");
        }

        recommendations.append("- Regular follow-up recommended.\n");
        recommendations.append("- Consider additional imaging for unclear areas.\n");
        
        return recommendations.toString();
    }
} 
package com.xrayai.service;

import com.xrayai.model.Anomaly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class SimpleAIModelService implements AIModelService {
    private static final Logger logger = LoggerFactory.getLogger(SimpleAIModelService.class);

    @Override
    public List<Anomaly> detectAnomalies(MultipartFile xrayImage) {
        logger.debug("Detecting anomalies in x-ray image: {}", xrayImage.getOriginalFilename());
        // Placeholder implementation
        List<Anomaly> anomalies = List.of(
            new Anomaly("Cavity", "Upper Right Molar", 0.95),
            new Anomaly("Decay", "Lower Left Incisor", 0.85)
        );
        logger.debug("Detected {} anomalies", anomalies.size());
        return anomalies;
    }
} 
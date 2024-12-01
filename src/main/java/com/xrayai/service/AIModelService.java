package com.xrayai.service;

import com.xrayai.model.Anomaly;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface AIModelService {
    List<Anomaly> detectAnomalies(MultipartFile xrayImage);
} 
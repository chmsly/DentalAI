package com.dentalai.model;

import java.util.List;

public class AIModelResult {
    private List<Anomaly> detectedAnomalies;
    private double confidenceScore;

    public AIModelResult(List<Anomaly> detectedAnomalies, double confidenceScore) {
        this.detectedAnomalies = detectedAnomalies;
        this.confidenceScore = confidenceScore;
    }

    public List<Anomaly> getDetectedAnomalies() {
        return detectedAnomalies;
    }

    public void setDetectedAnomalies(List<Anomaly> detectedAnomalies) {
        this.detectedAnomalies = detectedAnomalies;
    }

    public double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}

class Anomaly {
    private String type;
    private String location;
    private double severity;

    public Anomaly(String type, String location, double severity) {
        this.type = type;
        this.location = location;
        this.severity = severity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSeverity() {
        return severity;
    }

    public void setSeverity(double severity) {
        this.severity = severity;
    }
}

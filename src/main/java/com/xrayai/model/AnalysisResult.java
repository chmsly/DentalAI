package com.xrayai.model;

import java.util.List;

public class AnalysisResult {
    private List<Anomaly> aiDetectedAnomalies;
    private List<Anomaly> dentistAnomalies;
    private double agreementScore;
    private String recommendations;

    public AnalysisResult() {
    }

    public AnalysisResult(List<Anomaly> aiDetectedAnomalies, List<Anomaly> dentistAnomalies,
                         double agreementScore, String recommendations) {
        this.aiDetectedAnomalies = aiDetectedAnomalies;
        this.dentistAnomalies = dentistAnomalies;
        this.agreementScore = agreementScore;
        this.recommendations = recommendations;
    }

    public List<Anomaly> getAiDetectedAnomalies() {
        return aiDetectedAnomalies;
    }

    public void setAiDetectedAnomalies(List<Anomaly> aiDetectedAnomalies) {
        this.aiDetectedAnomalies = aiDetectedAnomalies;
    }

    public List<Anomaly> getDentistAnomalies() {
        return dentistAnomalies;
    }

    public void setDentistAnomalies(List<Anomaly> dentistAnomalies) {
        this.dentistAnomalies = dentistAnomalies;
    }

    public double getAgreementScore() {
        return agreementScore;
    }

    public void setAgreementScore(double agreementScore) {
        this.agreementScore = agreementScore;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
} 
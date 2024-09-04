package com.example.dentalxray.model;

import java.util.List;

public class ComparisonResult {
    private List<Anomaly> aiDetectedAnomalies;
    private List<Anomaly> dentistDetectedAnomalies;
    private List<Anomaly> missedAnomalies;
    private double agreementScore;

    public ComparisonResult(List<Anomaly> aiDetectedAnomalies, List<Anomaly> dentistDetectedAnomalies, 
                            List<Anomaly> missedAnomalies, double agreementScore) {
        this.aiDetectedAnomalies = aiDetectedAnomalies;
        this.dentistDetectedAnomalies = dentistDetectedAnomalies;
        this.missedAnomalies = missedAnomalies;
        this.agreementScore = agreementScore;
    }

    public List<Anomaly> getAiDetectedAnomalies() {
        return aiDetectedAnomalies;
    }

    public void setAiDetectedAnomalies(List<Anomaly> aiDetectedAnomalies) {
        this.aiDetectedAnomalies = aiDetectedAnomalies;
    }

    public List<Anomaly> getDentistDetectedAnomalies() {
        return dentistDetectedAnomalies;
    }

    public void setDentistDetectedAnomalies(List<Anomaly> dentistDetectedAnomalies) {
        this.dentistDetectedAnomalies = dentistDetectedAnomalies;
    }

    public List<Anomaly> getMissedAnomalies() {
        return missedAnomalies;
    }

    public void setMissedAnomalies(List<Anomaly> missedAnomalies) {
        this.missedAnomalies = missedAnomalies;
    }

    public double getAgreementScore() {
        return agreementScore;
    }

    public void setAgreementScore(double agreementScore) {
        this.agreementScore = agreementScore;
    }
}
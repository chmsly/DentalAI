package com.dentalai.model;

import java.time.LocalDateTime;

public class AnalysisReport {
    private String id;
    private String xrayId;
    private String userId;
    private ComparisonResult comparisonResult;
    private LocalDateTime analysisDate;
    private String recommendations;

    public AnalysisReport(String id, String xrayId, String userId, ComparisonResult comparisonResult, 
                          LocalDateTime analysisDate, String recommendations) {
        this.id = id;
        this.xrayId = xrayId;
        this.userId = userId;
        this.comparisonResult = comparisonResult;
        this.analysisDate = analysisDate;
        this.recommendations = recommendations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXrayId() {
        return xrayId;
    }

    public void setXrayId(String xrayId) {
        this.xrayId = xrayId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ComparisonResult getComparisonResult() {
        return comparisonResult;
    }

    public void setComparisonResult(ComparisonResult comparisonResult) {
        this.comparisonResult = comparisonResult;
    }

    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}

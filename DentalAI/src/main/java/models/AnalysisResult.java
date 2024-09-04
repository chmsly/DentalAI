package com.example.dentalxray.model;

import java.awt.image.BufferedImage;

public class AnalysisResult {
    private AnalysisReport report;
    private BufferedImage heatmap;

    public AnalysisResult(AnalysisReport report, BufferedImage heatmap) {
        this.report = report;
        this.heatmap = heatmap;
    }

    public AnalysisReport getReport() {
        return report;
    }

    public void setReport(AnalysisReport report) {
        this.report = report;
    }

    public BufferedImage getHeatmap() {
        return heatmap;
    }

    public void setHeatmap(BufferedImage heatmap) {
        this.heatmap = heatmap;
    }
}
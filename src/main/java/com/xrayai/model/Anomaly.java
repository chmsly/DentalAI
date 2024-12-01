package com.xrayai.model;

public class Anomaly {
    private String type;
    private String location;
    private double confidence;

    public Anomaly() {}

    public Anomaly(String type, String location, double confidence) {
        this.type = type;
        this.location = location;
        this.confidence = confidence;
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

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    @Override
    public String toString() {
        return "Anomaly{" +
                "type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", confidence=" + confidence +
                '}';
    }
} 
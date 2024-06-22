package com.example.dentalxray.dto;

public class XrayUploadResponseDTO {
    private String message;
    private String s3Url;

    public XrayUploadResponseDTO(String message, String s3Url) {
        this.message = message;
        this.s3Url = s3Url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getS3Url() {
        return s3Url;
    }

    public void setS3Url(String s3Url) {
        this.s3Url = s3Url;
    }
}

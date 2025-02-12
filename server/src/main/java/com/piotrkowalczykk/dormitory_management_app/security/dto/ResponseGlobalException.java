package com.piotrkowalczykk.dormitory_management_app.security.dto;

import java.time.LocalDateTime;

public class ResponseGlobalException {
    private Integer statusCode;
    private String message;
    private LocalDateTime timestamp;

    public ResponseGlobalException(Integer statusCode, String message, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

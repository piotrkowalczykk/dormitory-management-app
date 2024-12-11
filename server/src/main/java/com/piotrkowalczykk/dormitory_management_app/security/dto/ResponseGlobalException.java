package com.piotrkowalczykk.dormitory_management_app.security.dto;

import java.time.LocalDate;

public class ResponseGlobalException {
    private Integer statusCode;
    private String message;
    private LocalDate timestamp;

    public ResponseGlobalException(Integer statusCode, String message, LocalDate timestamp) {
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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
}

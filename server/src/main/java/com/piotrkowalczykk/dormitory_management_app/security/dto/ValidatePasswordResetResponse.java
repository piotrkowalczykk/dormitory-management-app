package com.piotrkowalczykk.dormitory_management_app.security.dto;

public class ValidatePasswordResetResponse {
    private String message;

    public ValidatePasswordResetResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

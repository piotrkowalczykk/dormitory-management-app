package com.piotrkowalczykk.dormitory_management_app.security.dto;

public class ValidateEmailResponse {
    private String message;

    public ValidateEmailResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

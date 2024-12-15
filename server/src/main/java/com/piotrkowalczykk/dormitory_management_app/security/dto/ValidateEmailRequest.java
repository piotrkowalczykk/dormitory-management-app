package com.piotrkowalczykk.dormitory_management_app.security.dto;

public class ValidateEmailRequest {
    private String emailCode;
    private String email;

    public ValidateEmailRequest(String emailCode, String email) {
        this.emailCode = emailCode;
        this.email = email;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

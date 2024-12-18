package com.piotrkowalczykk.dormitory_management_app.security.dto;

public class ValidatePasswordResetRequest {
    private String email;
    private String emailCode;
    private String newPassword;

    public ValidatePasswordResetRequest(String email, String emailCode, String newPassword) {
        this.email = email;
        this.emailCode = emailCode;
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

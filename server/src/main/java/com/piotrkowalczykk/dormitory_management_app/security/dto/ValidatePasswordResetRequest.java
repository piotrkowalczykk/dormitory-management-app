package com.piotrkowalczykk.dormitory_management_app.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ValidatePasswordResetRequest {
    private String email;
    @NotBlank(message = "code is mandatory")
    private String emailCode;
    @NotBlank(message = "password is mandatory")
    @Size(min = 8, message = "password must be at least 8 characters")
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

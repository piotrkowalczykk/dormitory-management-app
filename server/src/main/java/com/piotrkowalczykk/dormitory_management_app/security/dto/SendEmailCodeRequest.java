package com.piotrkowalczykk.dormitory_management_app.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SendEmailCodeRequest {
    @NotBlank(message = "email address is mandatory")
    @Email(message = "email address is not valid")
    private String email;

    public SendEmailCodeRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

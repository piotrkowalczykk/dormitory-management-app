package com.piotrkowalczykk.dormitory_management_app.security.dto;

public class SendEmailRequest {
    private String email;

    public SendEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

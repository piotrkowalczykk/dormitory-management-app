package com.piotrkowalczykk.dormitory_management_app.admin.dto;

public class AddAcademyResponse {
    private String message;

    public AddAcademyResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

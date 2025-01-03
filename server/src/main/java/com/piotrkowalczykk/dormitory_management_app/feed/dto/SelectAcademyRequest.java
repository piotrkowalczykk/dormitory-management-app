package com.piotrkowalczykk.dormitory_management_app.feed.dto;

public class SelectAcademyRequest {
    private String email;
    private Long academyId;

    public SelectAcademyRequest(String email, Long academyId) {
        this.email = email;
        this.academyId = academyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Long academyId) {
        this.academyId = academyId;
    }
}

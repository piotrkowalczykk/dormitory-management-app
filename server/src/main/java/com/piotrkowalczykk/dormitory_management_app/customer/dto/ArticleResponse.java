package com.piotrkowalczykk.dormitory_management_app.customer.dto;

import java.time.LocalDate;

public class ArticleResponse {
    private Long id;
    private String title;
    private String description;
    private String image;
    private LocalDate creationDate;
    private String content;

    public ArticleResponse(Long id, String title, String description, String image, LocalDate creationDate, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.creationDate = creationDate;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

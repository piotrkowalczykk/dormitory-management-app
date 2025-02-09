package com.piotrkowalczykk.dormitory_management_app.customer.dto;

import org.springframework.web.multipart.MultipartFile;

public class ArticleRequest {
    private String title;
    private String description;
    private String content;
    private MultipartFile image;

    public ArticleRequest(String title, String description, String content, MultipartFile image) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.image = image;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}

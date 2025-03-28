package com.piotrkowalczykk.dormitory_management_app.customer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.piotrkowalczykk.dormitory_management_app.customer.model.Dormitory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponse {
    private Long id;
    private String title;
    private String description;
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastModifiedDate;
    private String content;
    private List<Dormitory> visibleInDormitories;

    public ArticleResponse(Long id, String title, String description, String image, LocalDateTime creationDate, String content, LocalDateTime lastModifiedDate, List<Dormitory> visibleInDormitories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.creationDate = creationDate;
        this.content = content;
        this.lastModifiedDate = lastModifiedDate;
        this.visibleInDormitories = visibleInDormitories;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<Dormitory> getVisibleInDormitories() {
        return visibleInDormitories;
    }

    public void setVisibleInDormitories(List<Dormitory> visibleInDormitories) {
        this.visibleInDormitories = visibleInDormitories;
    }
}

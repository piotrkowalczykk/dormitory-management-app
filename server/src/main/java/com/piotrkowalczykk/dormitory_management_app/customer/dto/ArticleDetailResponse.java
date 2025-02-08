package com.piotrkowalczykk.dormitory_management_app.customer.dto;
import java.time.LocalDate;

public class ArticleDetailResponse {
    private Long id;
    private String title;
    private String content;
    private String image;
    private LocalDate creationDate;

    public ArticleDetailResponse(Long id, String title, String content, String image, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.creationDate = creationDate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}

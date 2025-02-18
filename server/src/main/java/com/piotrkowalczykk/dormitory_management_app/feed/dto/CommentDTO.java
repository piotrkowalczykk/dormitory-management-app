package com.piotrkowalczykk.dormitory_management_app.feed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CommentDTO {
    private Long id;
    private String content;
    private PublicUserDTO author;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastModifiedDate;

    public CommentDTO(Long id, String content, PublicUserDTO author, LocalDateTime creationDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PublicUserDTO getAuthor() {
        return author;
    }

    public void setAuthor(PublicUserDTO author) {
        this.author = author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

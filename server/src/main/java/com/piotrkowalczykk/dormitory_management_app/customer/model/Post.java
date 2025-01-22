package com.piotrkowalczykk.dormitory_management_app.customer.model;

import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String content;
    private String image;
    @ManyToOne(cascade = CascadeType.ALL)
    private AuthUser author;
    private LocalDateTime creationDate;

    public Post(Long id, String title, String description, String content, String image, AuthUser author, LocalDateTime creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.image = image;
        this.author = author;
        this.creationDate = creationDate;
    }

    public Post(){

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

    public AuthUser getAuthor() {
        return author;
    }

    public void setAuthor(AuthUser author) {
        this.author = author;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}

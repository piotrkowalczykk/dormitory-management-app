package com.piotrkowalczykk.dormitory_management_app.feed.model;

import com.piotrkowalczykk.dormitory_management_app.security.model.AuthUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String content;
    private String image;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthUser author;
    @CreationTimestamp
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;

    public Post(String content, String image, AuthUser author) {
        this.content = content;
        this.image = image;
        this.author = author;
    }

    public Post(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty String getContent() {
        return content;
    }

    public void setContent(@NotEmpty String content) {
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

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

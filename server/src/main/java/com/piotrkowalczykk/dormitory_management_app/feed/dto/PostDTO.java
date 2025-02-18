package com.piotrkowalczykk.dormitory_management_app.feed.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class PostDTO {
    private Long id;
    private String content;
    private String image;
    private PublicUserDTO author;
    private Set<PublicUserDTO> likedUsers;
    private List<CommentDTO> comments;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastModifiedDate;

    public PostDTO(Long id, String content, String image, PublicUserDTO author, Set<PublicUserDTO> likedUsers, List<CommentDTO> comments, LocalDateTime creationDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.content = content;
        this.image = image;
        this.author = author;
        this.likedUsers = likedUsers;
        this.comments = comments;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PublicUserDTO getAuthor() {
        return author;
    }

    public void setAuthor(PublicUserDTO author) {
        this.author = author;
    }

    public Set<PublicUserDTO> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(Set<PublicUserDTO> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
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

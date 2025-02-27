package com.piotrkowalczykk.dormitory_management_app.feed.dto;

import org.springframework.web.multipart.MultipartFile;

public class PostRequest {
    private String content;
    private MultipartFile image;

    public PostRequest(String content, MultipartFile image) {
        this.content = content;
        this.image = image;
    }

    public PostRequest(){

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

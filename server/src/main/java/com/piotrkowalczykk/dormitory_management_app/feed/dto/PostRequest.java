package com.piotrkowalczykk.dormitory_management_app.feed.dto;

public class PostRequest {
    private String content;
    private String image;

    public PostRequest(String content, String image) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

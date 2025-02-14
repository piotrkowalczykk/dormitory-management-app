package com.piotrkowalczykk.dormitory_management_app.feed.dto;

public class CommentRequest {
    private String content;

    public CommentRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

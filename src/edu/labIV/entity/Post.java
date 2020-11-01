package edu.labIV.entity;

import java.time.LocalDateTime;

public class Post {

    public final static int MAX_TEXT_LENGTH = 140;

    private int userId;
    private int postId;
    private String text;
    private String url;
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;        
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Post(int userId, String text, String url, LocalDateTime date) {
        this.userId = userId;
        this.text = text;
        this.url = url;
        this.date = date;
    }

    public Post() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

package com.emcove.rest.api.Core.response;

public class Comment {
    private Integer id;
    private String username;
    private String title;
    private String description;
    private CommentValue value;

    public Comment() {
    }

    public Comment(Integer id, String username, String title, String description, CommentValue value) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.description = description;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public CommentValue getValue() {
        return value;
    }

    public void setValue(CommentValue value) {
        this.value = value;
    }
}

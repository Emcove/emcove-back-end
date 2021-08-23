package com.emcove.rest.api.Core.response;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private CommentValue value;
    private LocalDateTime creationDateTime;
    public Comment() {
        creationDateTime = LocalDateTime.now();
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

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }


}

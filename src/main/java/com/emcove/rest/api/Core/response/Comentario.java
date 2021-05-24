package com.emcove.rest.api.Core.response;

public class Comentario {
    private Integer id;
    private String username;
    private String title;
    private String description;
    private ValorComentario value;

    public Comentario() {
    }

    public Comentario(Integer id, String username, String title, String description, ValorComentario value) {
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

    public ValorComentario getValue() {
        return value;
    }

    public void setValue(ValorComentario value) {
        this.value = value;
    }
}

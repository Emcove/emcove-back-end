package com.emcove.rest.api.Core.dto;

import com.emcove.rest.api.Core.response.Category;

import java.util.Set;

public class EntrepreneurshipDTO {
    private Integer id;
    private String name;
    private String description;
    private Set<Category> categories;

    public EntrepreneurshipDTO(Integer id, String name, String description, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}

package com.emcove.rest.api.Core.dto;

import com.emcove.rest.api.Core.response.Category;

import java.util.Set;

public class EntrepreneurshipDTO {
    private Integer id;
    private String name;
    private Set<Category> categories;
    private String logo;
    private String city;
    private Boolean doesShipments;

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

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getDoesShipments() {
        return doesShipments;
    }

    public void setDoesShipments(Boolean doesShipments) {
        this.doesShipments = doesShipments;
    }
}

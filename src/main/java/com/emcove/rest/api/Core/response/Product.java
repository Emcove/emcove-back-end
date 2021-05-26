package com.emcove.rest.api.Core.response;

import com.google.gson.Gson;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Float price;

    public Product() {}
    public Product(Integer id, String name, String description, Float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
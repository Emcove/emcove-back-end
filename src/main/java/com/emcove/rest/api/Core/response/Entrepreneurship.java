package com.emcove.rest.api.Core.response;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name="Entrepreneurships")
public class Entrepreneurship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String name;
    private String description ;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Reputation reputation;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Category> categories = new HashSet<>();


    public Entrepreneurship() {}

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public  boolean addProduct(Product product){
        return getProducts().add(product);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reputation getReputation() {
        return reputation;
    }

    public void setReputation(Reputation reputation) {
        this.reputation = reputation;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}


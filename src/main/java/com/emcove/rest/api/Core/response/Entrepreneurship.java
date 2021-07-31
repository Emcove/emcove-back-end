package com.emcove.rest.api.Core.response;


import com.emcove.rest.api.Core.utilities.NoBadWord;
import com.mercadopago.resources.Preference;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Entrepreneurships")
public class Entrepreneurship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @NoBadWord
    private String name;
    @Lob
    private String logo;
    @NoBadWord
    private String city;
    private Boolean doesShipments;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Reputation reputation = new Reputation();
    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Category> categories = new HashSet<>();
    private Boolean hasSuscription;

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public  boolean addProduct(Product product){
        return getProducts().add(product);
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

    public boolean hasProduct(final Product product) {
        return getProducts().contains(product);
    }
}


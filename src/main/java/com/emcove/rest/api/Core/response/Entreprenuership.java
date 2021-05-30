package com.emcove.rest.api.Core.response;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Entreprenuerships")
public class Entreprenuership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String name;
    private String description ;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
    @OneToOne(cascade = CascadeType.ALL)
    private Reputation reputation;

    public Entreprenuership() {}

    public Entreprenuership(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        //Ver como se inicializa, se deberia autogenerar el id
        this.reputation = new Reputation();
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
}


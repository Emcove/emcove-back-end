package com.emcove.rest.api.Core.response;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="Entreprenuerships")
public class Entreprenuership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String name;
    private String description ;
    @OneToMany
    private Set<Product> products = new HashSet<>();
    @OneToOne
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

    public void setDescription(String description) {
        this.description = description;
    }
}

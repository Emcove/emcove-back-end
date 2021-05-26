package com.emcove.rest.api.Core.response;

import java.util.HashSet;
import java.util.Set;

public class Emprendimiento {
    private Integer id ;
    private String name;
    private String description ;
    private Set<Producto> products = new HashSet<>();
    private Reputacion reputation;

    public Emprendimiento() {}

    public Emprendimiento(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        //Ver como se inicializa, se deberia autogenerar el id
        this.reputation = new Reputacion();
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

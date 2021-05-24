package com.emcove.rest.api.Core.response;

import java.util.HashSet;
import java.util.Set;

public class Emprendimiento {
    private Integer id ;
    private String nombre;
    private String descripcion;
    private Set<Producto> products = new HashSet<>();
    private Reputacion reputation;

    public Emprendimiento() {}

    public Emprendimiento(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        //Ver como se inicializa, se deberia autogenerar el id
        this.reputation = new Reputacion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

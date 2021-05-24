package com.emcove.rest.api.Core.response;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Pedido {
    private Integer id;
    private LocalDate createDate;
    private LocalDate deliverDate;
    private EstadoPedido state;
    private Set<Producto> products = new HashSet<>();

    public Pedido() {
    }

    public Pedido(Integer id, LocalDate createDate, LocalDate deliverDate, EstadoPedido state, Set<Producto> products) {
        this.id = id;
        this.createDate = createDate;
        this.deliverDate = deliverDate;
        this.state = state;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(LocalDate deliverDate) {
        this.deliverDate = deliverDate;
    }

    public EstadoPedido getState() {
        return state;
    }

    public void setState(EstadoPedido state) {
        this.state = state;
    }
}

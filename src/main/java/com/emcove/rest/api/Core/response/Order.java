package com.emcove.rest.api.Core.response;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Order {
    private Integer id;
    private LocalDate createDate;
    private LocalDate deliverDate;
    private OrderState state;
    private Product product;

    public Order() {
    }

    public Order(Integer id, LocalDate createDate, LocalDate deliverDate, OrderState state, Product product) {
        this.id = id;
        this.createDate = createDate;
        this.deliverDate = deliverDate;
        this.state = state;
        this.product = product;
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

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

}

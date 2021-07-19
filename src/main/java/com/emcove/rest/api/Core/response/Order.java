package com.emcove.rest.api.Core.response;

import com.google.gson.Gson;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate createDate;
    private LocalDate deliverDate;

    @OneToOne(mappedBy = "user_id")
    private User user;

    @OneToOne(mappedBy = "entrepreneurship_id")
    private Entrepreneurship entrepreneurship;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<TrackingData> trackingData;

    @OneToOne(mappedBy = "product_id")
    private Product product;

    @OneToOne
    private ProductSnapshot productSnapshot;

    public Order() {
    }

    public Order(Product product) {
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



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

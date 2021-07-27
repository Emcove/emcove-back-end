package com.emcove.rest.api.Core.response;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate createDate;
    private LocalDate deliverDate;

    @OneToOne
    private User user;

    @OneToOne
    private Entrepreneurship entrepreneurship;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderTrackingData> orderTrackingDatas;

    @OneToOne
    private ProductSnapshot productSnapshot;

    @OneToOne
    private Product product;

    public Order() {
        this.createDate = LocalDate.now();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Entrepreneurship getEntrepreneurship() {
        return entrepreneurship;
    }

    public void setEntrepreneurship(Entrepreneurship entrepreneurship) {
        this.entrepreneurship = entrepreneurship;
    }

    public List<OrderTrackingData> getOrderTrackingData() {
        return orderTrackingDatas;
    }

    public void setOrderTrackingData(List<OrderTrackingData> orderTrackingDatas) {
        this.orderTrackingDatas = orderTrackingDatas;
    }

    public ProductSnapshot getProductSnapshot() {
        return productSnapshot;
    }

    public void setProductSnapshot(ProductSnapshot productSnapshot) {
        this.productSnapshot = productSnapshot;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void addTrackingData(OrderTrackingData orderTrackingData) {
        orderTrackingDatas.add(orderTrackingData);
    }
}

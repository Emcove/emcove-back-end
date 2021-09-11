package com.emcove.rest.api.Core.response;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate createDate;
    private LocalDate deliverDate;
    @Enumerated(EnumType.STRING)
    private OrderState currentState;
    private String details;

    @OneToOne
    private User user;

    @OneToOne
    private Entrepreneurship entrepreneurship;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderTrackingData> orderTrackingDatas;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductSnapshot productSnapshot;

    @OneToOne
    private Product product;

    @OneToOne
    private DeliveryPoint userDeliveryPoint;

    @OneToOne
    private DeliveryPoint entrepreneurshipDeliveryPoint;

    public Order() {
        this.createDate = LocalDate.now();
        this.orderTrackingDatas = new ArrayList<>();
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
        OrderTrackingData orderTrackingDataToFind = orderTrackingDatas.stream().filter(tracking -> tracking.getState().equals(orderTrackingData.getState())).findFirst().orElse(null);

        if(orderTrackingDataToFind != null)
            throw new IllegalArgumentException("El pedido ya contiene el estado: " + orderTrackingData.getState().name() );

        orderTrackingDatas.add(orderTrackingData);

        setCurrentState(orderTrackingData.getState());
    }

    public void setCurrentState(OrderState state) {
        this.currentState = state;
    }

    public OrderState getCurrentState() {
        return currentState;
    }

    public String getDetails() { return details; }

    public void setDetails(String details) { this.details = details; }

    public boolean isClosed() {
        return currentState.equals(OrderState.ENTREGADO) || currentState.equals(OrderState.CANCELADO) || currentState.equals(OrderState.RECHAZADO);
    }

    public DeliveryPoint getUserDeliveryPoint() {
        return userDeliveryPoint;
    }

    public void setUserDeliveryPoint(DeliveryPoint userDeliveryPoint) {
        this.userDeliveryPoint = userDeliveryPoint;
    }

    public DeliveryPoint getEntrepreneurshipDeliveryPoint() {
        return entrepreneurshipDeliveryPoint;
    }

    public void setEntrepreneurshipDeliveryPoint(DeliveryPoint entrepreneurshipDeliveryPoint) {
        this.entrepreneurshipDeliveryPoint = entrepreneurshipDeliveryPoint;
    }
}

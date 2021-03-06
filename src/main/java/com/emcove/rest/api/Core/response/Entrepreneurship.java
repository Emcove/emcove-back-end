package com.emcove.rest.api.Core.response;


import com.emcove.rest.api.Core.dto.SubscriptionPlanDTO;
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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    private String hasSubscription = "0";
    private Date subscriptionExpirationDate;
    private String facebook_page_id;
    private String googleCalendarId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "entrepreneurship_id")
    private List<DeliveryPoint> deliveryPoints;

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

    public String getHasSubscription() { return hasSubscription; }

    public void setHasSubscription(String hasSubscription) { this.hasSubscription = hasSubscription; }

    public Date getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public void setSubscriptionExpirationDate(Date subscriptionExpirationDate) {
        this.subscriptionExpirationDate = subscriptionExpirationDate;
    }

    public String getGoogleCalendarId() {
        return googleCalendarId;
    }

    public void setGoogleCalendarId(String googleCalendarId) {
        this.googleCalendarId = googleCalendarId;
    }

    public void subscribe(SubscriptionPlanDTO plan) {
        this.hasSubscription = "1";
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        switch(plan.getName()) {
            case "month":
                calendar.add(Calendar.MONTH, 1);
                break;
            case "6-month":
                calendar.add(Calendar.MONTH, 6);
                break;
            case "annual":
                calendar.add(Calendar.YEAR, 1);
                break;
        }

        this.subscriptionExpirationDate = calendar.getTime();
    }

    public String getFacebook_page_id() {
        return facebook_page_id;
    }

    public void setFacebook_page_id(String facebook_page_id) {
        this.facebook_page_id = facebook_page_id;
    }

    public List<DeliveryPoint> getDeliveryPoints() {
        return deliveryPoints;
    }

    public void setDeliveryPoints(List<DeliveryPoint> deliveryPoints) {
        this.deliveryPoints = deliveryPoints;
    }

    public boolean addDeliveryPoint(DeliveryPoint deliveryPoint){
        return deliveryPoints.add(deliveryPoint);
    }
}


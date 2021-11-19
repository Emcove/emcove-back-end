package com.emcove.rest.api.Core.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    //@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Entrepreneurship entrepreneurship;
    @OneToOne(cascade = CascadeType.ALL)
    private Reputation reputation;
    private int enabled = 1;
    private String name;
    private String surname;
    private String city;
    @Lob
    private String avatar;
    private Boolean adult;

    private Boolean hasEntrepreneurship = false;
    private String entrepreneurshipName;

    @OneToMany(cascade = CascadeType.ALL)
    List<DeliveryPoint> deliveryPoints;

    public User(){}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Reputation getReputation() {
        return this.reputation;
    }

    public void setReputation(Reputation reputation) {
        this.reputation = reputation;
    }

    public Entrepreneurship getEntrepreneurship() {
        return entrepreneurship;
    }

    public void setEntrepreneurship(Entrepreneurship entrepreneurship) {
        this.entrepreneurship = entrepreneurship;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
         this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public boolean hasEntrepreneuship(){ return getEntrepreneurship() != null;}

    public List<DeliveryPoint> getDeliveryPoints() {
        return deliveryPoints;
    }

    public void setDeliveryPoints(List<DeliveryPoint> deliveryPoints) {
        this.deliveryPoints = deliveryPoints;
    }

    public boolean addDeliveryPoint(DeliveryPoint deliveryPoint){
        return deliveryPoints.add(deliveryPoint);
    }

    public Boolean getHasEntrepreneurship() {
        return hasEntrepreneurship;
    }

    public void setHasEntrepreneurship(Boolean hasEntrepreneurship) {
        this.hasEntrepreneurship = hasEntrepreneurship;
    }

    public String getEntrepreneurshipName() {
        return entrepreneurshipName;
    }

    public void setEntrepreneurshipName(String entrepreneurshipName) {
        this.entrepreneurshipName = entrepreneurshipName;
    }
}

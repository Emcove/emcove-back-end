package com.emcove.rest.api.Core.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

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
    @OneToOne
    private Entrepreneurship entrepreneurship;
    @OneToOne(cascade = CascadeType.ALL)
    private Reputation reputation;
    private final int enabled = 1;
    private String name;
    private String surname;
    private String city;
    private String avatar;
    private Boolean adult;

    public User(){}

    public User(String userName, String password, String email) {
        this.username = userName;
        this.password = password;
        this.email = email;
        //Ver como se inicializa, se deberia autogenerar el id
        this.reputation = new Reputation();
    }
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

    public Entrepreneurship getEmprendimiento() {
        return entrepreneurship;
    }

    public void setEmprendimiento(Entrepreneurship entrepreneurship) {
        this.entrepreneurship = entrepreneurship;
    }

    public Reputation getReputation() {
        return this.reputation;
    }

    public void setReputation(Reputation reputation) {
        this.reputation = reputation;
    }
}

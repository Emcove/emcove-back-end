package com.emcove.rest.api.Core.response;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String email;
    @OneToOne
    private Entreprenuership entreprenuership;
    @OneToOne
    private Reputation reputation;

    public User(){}

    public User(String userName, String password, String email) {
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    public Entreprenuership getEmprendimiento() {
        return entreprenuership;
    }

    public void setEmprendimiento(Entreprenuership entreprenuership) {
        this.entreprenuership = entreprenuership;
    }
}

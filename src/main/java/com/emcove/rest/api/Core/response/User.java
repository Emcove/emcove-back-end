package com.emcove.rest.api.Core.response;

public class User {
    private String userName;
    private String password;
    private String email;
    private Entreprenuership entreprenuership;
    private Reputation reputation;

    public User(){}

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        //Ver como se inicializa, se deberia autogenerar el id
        this.reputation = new Reputation();
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

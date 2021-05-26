package com.emcove.rest.api.Core.response;

public class Usuario {
    private String userName;
    private String password;
    private String email;
    private Emprendimiento emprendimiento;
    private Reputacion reputation;

    public Usuario(){}

    public Usuario(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        //Ver como se inicializa, se deberia autogenerar el id
        this.reputation = new Reputacion();
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
    public Emprendimiento getEmprendimiento() {
        return emprendimiento;
    }

    public void setEmprendimiento(Emprendimiento emprendimiento) {
        this.emprendimiento = emprendimiento;
    }
}

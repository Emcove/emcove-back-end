package com.emcove.rest.api.Core.response;

import java.util.HashSet;
import java.util.Set;

public class Reputacion {
    private Integer id;
    private Float averagePoints;
    private int totalPoint;
    private Set<Comentario> coments = new HashSet<>();

    public Reputacion(Integer id, Float averagePoints, int totalPoint) {
        this.id = id;
        this.averagePoints = averagePoints;
        this.totalPoint = totalPoint;
    }

    public Reputacion() {
        averagePoints = 0F;
        totalPoint = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getAveragePoints() {
        return averagePoints;
    }

    public void setAveragePoints(Float averagePoints) {
        this.averagePoints = averagePoints;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

    public Set<Comentario> getComents() {
        return coments;
    }

    public void setComents(Set<Comentario> coments) {
        this.coments = coments;
    }

    public void addComent(Comentario coment){
        getComents().add(coment);
        calcularReputacion(coment.getValue().getValue());
    }

    public void calcularReputacion(int newValue){
        setTotalPoint(getTotalPoint() + newValue);
        setAveragePoints((float) (getTotalPoint() / (getComents().size()+1)));
    }
}

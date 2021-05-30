package com.emcove.rest.api.Core.response;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "Reputations")
public class Reputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float averagePoints;
    private int totalPoint;
    @OneToMany
    private Set<Comment> coments = new HashSet<>();

    public Reputation(Integer id, Float averagePoints, int totalPoint) {
        this.id = id;
        this.averagePoints = averagePoints;
        this.totalPoint = totalPoint;
    }

    public Reputation() {
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

    public Set<Comment> getComents() {
        return coments;
    }

    public void setComents(Set<Comment> coments) {
        this.coments = coments;
    }

    public void addComent(Comment coment){
        getComents().add(coment);
        calculateReputation(coment.getValue().getValue());
    }

    public void calculateReputation(int newValue){
        setTotalPoint(getTotalPoint() + newValue);
        setAveragePoints((float) (getTotalPoint() / (getComents().size()+1)));
    }
}

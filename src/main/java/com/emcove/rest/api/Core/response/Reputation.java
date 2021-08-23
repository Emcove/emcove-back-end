package com.emcove.rest.api.Core.response;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Reputations")
public class Reputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float averagePoints;
    private int totalPoint;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

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

    public List<Comment> getComments() {
        Collections.sort(comments, (comment1, comment2) -> comment1.getCreationDateTime().compareTo(comment2.getCreationDateTime()));
        return comments;
    }

    public void setComments(List<Comment> coments) {
        this.comments = coments;
    }

    public void addComent(Comment coment){
        getComments().add(coment);
        calculateReputation(coment.getValue().getValue());
    }

    public void calculateReputation(int newValue){
        setTotalPoint(getTotalPoint() + newValue);
        setAveragePoints((float)getTotalPoint() / (float) getComments().size());
    }
}

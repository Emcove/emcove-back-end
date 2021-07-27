package com.emcove.rest.api.Core.response;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="ProductSnapshots")
public class ProductSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productName;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = EAGER)
    private List<ChosenProp> chosenProps;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = EAGER)
    private List<Image> images;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<ChosenProp> getChosenProps() {
        return chosenProps;
    }

    public void setChosenProps(List<ChosenProp> chosenProps) {
        this.chosenProps = chosenProps;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}

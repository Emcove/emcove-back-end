package com.emcove.rest.api.Core.response;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Float price;
    @Lob
    private String image;

    @OneToMany(cascade = CascadeType.ALL)
    @CollectionTable(name="ProductProp")
    private Set<ProductProp> props = new HashSet<>();

    public Product(Set<ProductProp> props) {
        this.props = props;
    }
    public Product(Integer id, String name, String description, Float price, Set<ProductProp> props) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.props = props;
    }

    public Product() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<ProductProp> getProps() {
        return props;
    }

    public void setProps(Set<ProductProp> props) {
        this.props = props;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

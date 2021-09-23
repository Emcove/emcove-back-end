package com.emcove.rest.api.Core.response;



import com.emcove.rest.api.Core.utilities.NoBadWord;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NoBadWord
    private String name;
    @NoBadWord
    private String description;

    private Boolean immediateDelivery;

    private Integer productionTime;

    private Float basePrice;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = EAGER)
    private List<Image> images;

    @OneToMany(cascade = CascadeType.ALL)
    @CollectionTable(name="ProductProp")
    private Set<ProductProp> props ;

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

    public Set<ProductProp> getProps() {
        return props;
    }

    public void setProps(Set<ProductProp> props) {
        this.props = props;
    }

    public Boolean getImmediateDelivery() {
        return immediateDelivery;
    }

    public void setImmediateDelivery(Boolean immediateDelivery) {
        this.immediateDelivery = immediateDelivery;
    }

    public Integer getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Integer productionTime) {
        this.productionTime = productionTime;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this.getId() == null) return false;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        if (product.getId() == null) return false;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

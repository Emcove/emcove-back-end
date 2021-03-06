package com.emcove.rest.api.Core.response;

import com.emcove.rest.api.Core.utilities.NoBadWord;
import com.emcove.rest.api.Core.utilities.NoBadWordObject;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="ProductProps")
public class ProductProp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;
    @NoBadWord
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_prop_id")
    private List<ProductOption> options ;

    @NoBadWord(content = NoBadWordObject.List)
    public ProductProp() {
    }

    public ProductProp(List<ProductOption> options) {
        this.options = options;
    }

    public List<ProductOption> getOptions() {
        return options;
    }

    public void setOptions(List<ProductOption> options) {
        this.options = options;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductProp that = (ProductProp) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

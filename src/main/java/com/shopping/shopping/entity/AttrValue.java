package com.shopping.shopping.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "attr_value", schema = "shoe_shop")
public class AttrValue {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "attr_value_id")
    //@JsonIgnore
    private Long attrValueId;

    @Column(name = "attr_value")
    private String attrValue;

    //@JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    private Attribute attribute;

    /*@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_and_attr", schema = "shoe_shop",
            joinColumns = @JoinColumn(name = "attr_value_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSetByAttrValue;*/


    public Long getAttrValueId() {
        return attrValueId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    @Override
    public String toString() {
        return attrValue;
    }
}

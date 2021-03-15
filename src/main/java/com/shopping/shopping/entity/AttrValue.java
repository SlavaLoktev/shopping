package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "attr_value", schema = "shoe_shop"/*, catalog = "shoe_shop"*/)
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class AttrValue {
    private Long id;
    private Integer attrValueId;
    private Integer attributeId;
    private String attrValue;
    private Attribute attributeByAttributeId;
    private Collection<ProductAndAttr> productAndAttrsByAttrValueId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "attr_value_id")
    public Integer getAttrValueId() {
        return attrValueId;
    }


    @Basic
    @Column(name = "attribute_id")
    public Integer getAttributeId() {
        return attributeId;
    }


    @Basic
    @Column(name = "attr_value")
    public String getAttrValue() {
        return attrValue;
    }


    @ManyToOne
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    public Attribute getAttributeByAttributeId() {
        return attributeByAttributeId;
    }


    @OneToMany(mappedBy = "attrValueByAttrValueId")
    public Collection<ProductAndAttr> getProductAndAttrsByAttrValueId() {
        return productAndAttrsByAttrValueId;
    }
}

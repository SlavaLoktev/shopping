package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Attribute {

    private Long id;
    private Integer attributeId;
    private String attributeName;
    private Collection<AttrValue> attrValuesByAttributeId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "attribute_id")
    public Integer getAttributeId() {
        return attributeId;
    }


    @Basic
    @Column(name = "attribute_name")
    public String getAttributeName() {
        return attributeName;
    }


    @OneToMany(mappedBy = "attributeByAttributeId")
    public Collection<AttrValue> getAttrValuesByAttributeId() {
        return attrValuesByAttributeId;
    }
}

package com.shopping.shopping.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "attribute", schema = "shoe_shop")
public class Attribute {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "attribute_id")
    private Long attributeId;

    @Column(name = "attribute_name")
    private String attributeName;

    //@JsonBackReference
//    @OneToMany(mappedBy = "attribute", fetch = FetchType.LAZY)
//    List<AttrValue> attrValues;


    public Long getAttributeId() {
        return attributeId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public String toString() {
        return attributeName;
    }
}

package com.shopping.shopping.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

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
    private Long attrValueId;

    @Column(name = "attr_value")
    private String attrValue;

    @ManyToOne
    @JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")
    private Attribute attribute;

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

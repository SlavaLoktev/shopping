package com.shopping.shopping.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Data
@Entity
@Table(name = "attr_value", schema = "shoe_shop")
public class AttrValue {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "attr_value_id")
    private Long attrValueId;

    @Column(name = "attr_value")
    private String attrValue;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_and_attr", schema = "shoe_shop",
            joinColumns = @JoinColumn(name = "attr_value_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> productSetByAttrValue;

    @Override
    public String toString() {
        return attrValue;
    }
}

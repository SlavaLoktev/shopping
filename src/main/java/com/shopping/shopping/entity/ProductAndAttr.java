package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_and_attr", schema = "shoe_shop"/*, catalog = ""*/)
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class ProductAndAttr {
    private Long id;
    private Integer productId;
    private Integer attrValueId;
    private Product productByProductId;
    private AttrValue attrValueByAttrValueId;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }


    @Basic
    @Column(name = "attr_value_id")
    public Integer getAttrValueId() {
        return attrValueId;
    }



    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    public Product getProductByProductId() {
        return productByProductId;
    }


    @ManyToOne
    @JoinColumn(name = "attr_value_id", referencedColumnName = "attr_value_id")
    public AttrValue getAttrValueByAttrValueId() {
        return attrValueByAttrValueId;
    }

}

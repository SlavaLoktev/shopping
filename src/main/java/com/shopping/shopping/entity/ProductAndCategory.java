package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_and_category", schema = "shoe_shop")
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class ProductAndCategory {
    private Long id;
    private Integer productId;
    private Integer categoryId;
    private Product productByProductId;
    private Category categoryByCategoryId;

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
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    public Product getProductByProductId() {
        return productByProductId;
    }


    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

}

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
public class Product {
    private Long id;
    private Integer productId;
    private String productName;
    private String storageUnit;
    private Integer price;
    private Integer discountPrice;
    private String description;
    private String image;
    private String imageSmall;
    private Collection<ProductAndAttr> productAndAttrsByProductId;
    private Collection<ProductAndCategory> productAndCategoriesByProductId;
    private Collection<Reviews> reviewsByProductId;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }


    @Id
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }


    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }


    @Basic
    @Column(name = "storage_unit")
    public String getStorageUnit() {
        return storageUnit;
    }


    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }


    @Basic
    @Column(name = "discount_price")
    public Integer getDiscountPrice() {
        return discountPrice;
    }


    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }


    @Basic
    @Column(name = "image")
    public String getImage() {
        return image;
    }


    @Basic
    @Column(name = "image_small")
    public String getImageSmall() {
        return imageSmall;
    }



    @OneToMany(mappedBy = "productByProductId")
    public Collection<ProductAndAttr> getProductAndAttrsByProductId() {
        return productAndAttrsByProductId;
    }


    @OneToMany(mappedBy = "productByProductId")
    public Collection<ProductAndCategory> getProductAndCategoriesByProductId() {
        return productAndCategoriesByProductId;
    }


    @OneToMany(mappedBy = "productByProductId")
    public Collection<Reviews> getReviewsByProductId() {
        return reviewsByProductId;
    }

}

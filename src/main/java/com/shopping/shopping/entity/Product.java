package com.shopping.shopping.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "product", schema = "shoe_shop")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JoinColumn(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "storage_unit")
    private String storageUnit;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount_price")
    private Integer discountPrice;

    @Column(name = "description")
    private String description;

    /*@Column(name = "image")
    private String image;*/

    /*@Column(name = "image_small")
    private String imageSmall;*/



    /*@ManyToMany(mappedBy = "productSetByCategory")
    private List<Category> categoryList;*/

    //@JsonIgnore
    @ManyToMany
    @JoinTable(name = "product_and_category", schema = "shoe_shop",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;



    /*@ManyToMany(mappedBy = "productSetByAttrValue")
    private List<AttrValue> attrValueList;*/

    //@JsonIgnore
    @ManyToMany
    @JoinTable(name = "product_and_attr", schema = "shoe_shop",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "attr_value_id"))
    private Set<AttrValue> attrValues;



    //@JsonBackReference
//    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
//    //@JoinColumn(name = "product_id", referencedColumnName = "product_id")
//    List<Reviews> reviewsByProduct;

    @Override
    public String toString() {
        return productName;
    }
}

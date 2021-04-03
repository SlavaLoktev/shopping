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
import java.util.List;
import java.util.Set;


@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Data
@Entity
@Table(name = "product", schema = "shoe_shop")
public class Product {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "product_id")
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

    @ManyToMany(mappedBy = "productSetByCategory")
    private List<Category> categoryList;

    @ManyToMany(mappedBy = "productSetByAttrValue")
    private List<AttrValue> attrValueList;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    List<Reviews> reviewsByProduct;

    @Override
    public String toString() {
        return productName;
    }
}

package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Reviews {
    private Long id;
    private Integer reviewId;
    private Integer customerId;
    private Integer productId;
    private String text;
    private Date reviewDate;
    private Integer rating;
    private Customers customersByCustomerId;
    private Product productByProductId;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }


    @Id
    @Column(name = "review_id")
    public Integer getReviewId() {
        return reviewId;
    }


    @Basic
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }


    @Basic
    @Column(name = "product_id")
    public Integer getProductId() {
        return productId;
    }


    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }


    @Basic
    @Column(name = "review_date")
    public Date getReviewDate() {
        return reviewDate;
    }


    @Basic
    @Column(name = "rating")
    public Integer getRating() {
        return rating;
    }


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    public Customers getCustomersByCustomerId() {
        return customersByCustomerId;
    }



    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    public Product getProductByProductId() {
        return productByProductId;
    }
}

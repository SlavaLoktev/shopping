package com.shopping.shopping.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Date;

@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "reviews", schema = "shoe_shop")
public class Reviews {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "text")
    private String text;

    @Column(name = "review_date")
    private Date reviewDate;

    @Column(name = "rating")
    private Integer rating;


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customers customers;

    public Long getReviewId() {
        return reviewId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public Integer getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "reviewId=" + reviewId +
                ", text='" + text + '\'' +
                '}';
    }
}

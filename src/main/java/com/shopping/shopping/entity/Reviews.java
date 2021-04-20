package com.shopping.shopping.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Date;

@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "reviews", schema = "shoe_shop")
public class Reviews {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "review_id")
    private Long reviewId;

    private String text;

    @Column(name = "review_date")
    private Date reviewDate;

    private Integer rating;

    //@JsonManagedReference
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;
}

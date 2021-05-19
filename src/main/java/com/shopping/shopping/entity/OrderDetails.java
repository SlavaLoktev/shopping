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
@Table(name = "order_details", schema = "shoe_shop")
public class OrderDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_order_to_order_details"), referencedColumnName = "order_id")
    private Orders order;

    @ManyToOne()
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_product_to_order_details"), referencedColumnName = "product_id")
    private Product product;

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Orders getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "order=" + order +
                ", product=" + product +
                '}';
    }
}

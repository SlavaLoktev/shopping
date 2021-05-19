package com.shopping.shopping.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "orders", schema = "shoe_shop")
public class Orders implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_first_name")
    private String customerFirstName;

    @Column(name = "customer_last_name")
    private String customerLastName;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_total")
    private Integer orderTotal;

    public Long getOrderId() {
        return orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "customerFirstName='" + customerFirstName + '\'' +
                ", customerLastName='" + customerLastName + '\'' +
                ", orderDate=" + orderDate +
                ", orderTotal=" + orderTotal +
                '}';
    }
}

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
@Table(name = "customers", schema = "shoe_shop")
public class Customers {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "cust_first_name")
    private String custFirstName;

    @Column(name = "cust_last_name")
    private String custLastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "cust_email")
    private String custEmail;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private String gender;

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCustEmail() {
        return custEmail;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerId=" + customerId +
                ", custFirstName='" + custFirstName + '\'' +
                ", custLastName='" + custLastName + '\'' +
                '}';
    }
}

package com.shopping.shopping.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Data
@Entity
@Table(name = "customers", schema = "shoe_shop")
public class Customers {

    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    List<Reviews> reviewsByCustomers;
}

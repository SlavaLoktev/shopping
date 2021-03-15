package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Customers {
    private Long id;
    private Integer customerId;
    private String custFirstName;
    private String custLastName;
    private Integer custCityId;
    private String phoneNumber;
    private String custEmail;
    private Date birthday;
    private String gender;
    private Cities citiesByCustCityId;
    private Collection<Reviews> reviewsByCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    @Id
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    @Basic
    @Column(name = "cust_first_name")
    public String getCustFirstName() {
        return custFirstName;
    }

    @Basic
    @Column(name = "cust_last_name")
    public String getCustLastName() {
        return custLastName;
    }


    @Basic
    @Column(name = "cust_city_id")
    public Integer getCustCityId() {
        return custCityId;
    }


    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }


    @Basic
    @Column(name = "cust_email")
    public String getCustEmail() {
        return custEmail;
    }


    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }


    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }



    @ManyToOne
    @JoinColumn(name = "cust_city_id", referencedColumnName = "cust_city_id")
    public Cities getCitiesByCustCityId() {
        return citiesByCustCityId;
    }



    @OneToMany(mappedBy = "customersByCustomerId")
    public Collection<Reviews> getReviewsByCustomerId() {
        return reviewsByCustomerId;
    }
}

package com.shopping.shopping.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Cities {
    private Long id;
    private Integer custCityId;
    private String cityName;
    private ShopAdress shopAdressByCityName;
    private Collection<Customers> customersByCustCityId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "cust_city_id")
    public Integer getCustCityId() {
        return custCityId;
    }


    @Basic
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }


    @ManyToOne
    @JoinColumn(name = "city_name", referencedColumnName = "city_name")
    public ShopAdress getShopAdressByCityName() {
        return shopAdressByCityName;
    }


    @OneToMany(mappedBy = "citiesByCustCityId")
    public Collection<Customers> getCustomersByCustCityId() {
        return customersByCustCityId;
    }

}

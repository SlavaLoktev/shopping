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
@Table(name = "shop_adress", schema = "shoe_shop"/*, catalog = ""*/)
public class ShopAdress {
    private Long id;
    private String cityName;
    private String adress;
    private Collection<Cities> citiesByCityName;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }


    @Id
    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }


    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }


    @OneToMany(mappedBy = "shopAdressByCityName")
    public Collection<Cities> getCitiesByCityName() {
        return citiesByCityName;
    }
}

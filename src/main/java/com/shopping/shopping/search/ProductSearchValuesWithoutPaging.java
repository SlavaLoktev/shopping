package com.shopping.shopping.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductSearchValuesWithoutPaging {

    //поля поиска(все типы - объектные, не примитивные. чтобы можно было передать null)
    private String productName;
    private Integer price;//мб не нужно

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

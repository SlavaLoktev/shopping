package com.shopping.shopping.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ReviewsSearchValues {

    private Long product;

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}

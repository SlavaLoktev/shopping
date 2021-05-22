package com.shopping.shopping.repository;

import com.shopping.shopping.entity.Reviews;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CommonRepository<Reviews> {

    @Query("select r from Reviews r where" +
            "(:product is null or r.product.productId=:product)")
    List<Reviews> findByParams(@Param("product") Long product);
}

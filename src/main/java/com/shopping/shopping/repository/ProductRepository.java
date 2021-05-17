package com.shopping.shopping.repository;

import com.shopping.shopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByPriceAsc(); //сортировка по цене по возрастанию

    List<Product> findAllByOrderByPriceDesc(); //сортировка по цене по убыванию

    /*//если productName == null или =='', то получим все значения
    @Query("select p from Product p where " +
            "(:productName is null or :productName='' or lower(p.productName) like lower(concat('%', :productName, '%') ) ) " +
            "order by p.productName asc")
    // поиск по всем переданным параметрам (пустые параметры учитываться не будут)
    Page<Product> findByParams(@Param("productName") String productName, Pageable pageable);*/

    // поиск по всем переданным параметрам (пустые параметры учитываться не будут)
    @Query("select p from Product p where " +
            "(:productName is null or :productName='' or lower(p.productName) like lower(concat('%', :productName, '%') ) ) and" +
            "(:price is null or p.price=:price)" +
            "order by p.productName asc")
    Page<Product> findByParams(@Param("productName") String productName,
                               @Param("price") Integer price,
                               Pageable pageable);

    @Query("select p from Product p where " +
            "(:productName is null or :productName='' or lower(p.productName) like lower(concat('%', :productName, '%') ) ) and" +
            "(:price is null or p.price=:price)")
    List<Product> findByParamsWithoutPaging(@Param("productName") String productName,
                                            @Param("price") Integer price);

}

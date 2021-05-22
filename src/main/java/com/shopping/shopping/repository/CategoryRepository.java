package com.shopping.shopping.repository;

import com.shopping.shopping.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CommonRepository<Category> {

    @Query("select c from Category c where " +
            "(:departmentId is null or c.department.departmentId=:departmentId)")
    List<Category> findByParams(@Param("departmentId") Long departmentId);

}

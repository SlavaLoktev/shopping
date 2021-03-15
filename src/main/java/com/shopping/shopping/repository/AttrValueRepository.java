package com.shopping.shopping.repository;

import com.shopping.shopping.entity.AttrValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttrValueRepository extends JpaRepository<AttrValue, Long> {
}

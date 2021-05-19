package com.shopping.shopping.repository;

import com.shopping.shopping.entity.OrderDetails;
import com.shopping.shopping.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}
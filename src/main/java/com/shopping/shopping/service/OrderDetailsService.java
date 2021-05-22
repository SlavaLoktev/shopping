package com.shopping.shopping.service;

import com.shopping.shopping.entity.OrderDetails;
import com.shopping.shopping.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderDetailsService extends AbstractService<OrderDetails, OrderDetailsRepository>{

    public OrderDetailsService(OrderDetailsRepository repository) {
        super(repository);
    }
}

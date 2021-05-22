package com.shopping.shopping.service;

import com.shopping.shopping.entity.Orders;
import com.shopping.shopping.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrdersService extends AbstractService<Orders, OrdersRepository>{

    public OrdersService(OrdersRepository repository) {
        super(repository);
    }
}

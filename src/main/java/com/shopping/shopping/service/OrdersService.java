package com.shopping.shopping.service;

import com.shopping.shopping.entity.Orders;
import com.shopping.shopping.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrdersService {

    private final OrdersRepository repository;

    public OrdersService(OrdersRepository repository) {
        this.repository = repository;
    }

    public List<Orders> findAll(){
        return repository.findAll();
    }

    public Orders add(Orders orders){
        return repository.save(orders);
    }

    public Orders update(Orders orders){
        return repository.save(orders);
    }

    public Orders findById(Long id){
        return repository.findById(id).get();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}

package com.shopping.shopping.service;

import com.shopping.shopping.entity.OrderDetails;
import com.shopping.shopping.repository.OrderDetailsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderDetailsService {

    private final OrderDetailsRepository repository;

    public OrderDetailsService(OrderDetailsRepository repository) {
        this.repository = repository;
    }

    public List<OrderDetails> findAll(){
        return repository.findAll();
    }

    public OrderDetails add(OrderDetails orderDetails){
        return repository.save(orderDetails);
    }

    public OrderDetails update(OrderDetails orderDetails){
        return repository.save(orderDetails);
    }

    public OrderDetails findById(Long id){
        return repository.findById(id).get();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}

package com.shopping.shopping.service;

import com.shopping.shopping.entity.Customers;
import com.shopping.shopping.repository.CustomersRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomersService extends AbstractService<Customers, CustomersRepository>{

    public CustomersService(CustomersRepository repository) {
        super(repository);
    }
}

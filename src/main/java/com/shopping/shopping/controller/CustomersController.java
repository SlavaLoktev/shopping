package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Customers;
import com.shopping.shopping.repository.CustomersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private CustomersRepository customersRepository;

    public CustomersController(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @GetMapping("/test")
    public List<Customers> test(){

        List<Customers> list = customersRepository.findAll();

        return list;
    }
}

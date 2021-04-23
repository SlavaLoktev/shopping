package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Customers;
import com.shopping.shopping.repository.CustomersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<Customers> add(@RequestBody Customers customers){

        if(customers.getCustomerId() != null && customers.getCustomerId() != 0){
            return new ResponseEntity("redundand param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustFirstName() == null || customers.getCustFirstName().trim().length() == 0){
            return new ResponseEntity("missed param: custFirstName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustLastName() == null || customers.getCustLastName().trim().length() == 0){
            return new ResponseEntity("missed param: custLastName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getPhoneNumber() == null || customers.getPhoneNumber().trim().length() == 0){
            return new ResponseEntity("missed param: phoneNumber", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustEmail() == null || customers.getCustEmail().trim().length() == 0){
            return new ResponseEntity("missed param: custEmail", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(customersRepository.save(customers));
    }
}

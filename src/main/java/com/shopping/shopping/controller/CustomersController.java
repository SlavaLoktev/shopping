package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Customers;
import com.shopping.shopping.repository.CustomersRepository;
import com.shopping.shopping.service.CustomersService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/all")
    public List<Customers> findAll(){

        return customersService.findAll();
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

        return ResponseEntity.ok(customersService.add(customers));
    }

    @PutMapping("/update")
    public ResponseEntity<Customers> update(@RequestBody Customers customers){

        if(customers.getCustomerId() == null && customers.getCustomerId() == 0){
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
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

        return ResponseEntity.ok(customersService.update(customers));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Customers> findById(@PathVariable Long id){

        Customers customers = null;

        try {
            customers = customersService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            customersService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

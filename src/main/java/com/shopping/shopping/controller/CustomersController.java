package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Customers;
import com.shopping.shopping.service.CustomersService;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomersController {

    private final CustomersService customersService;

    private static Logger LOGGER = Logger.getLogger(ProductController.class);

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @GetMapping("/all")
    public List<Customers> findAll(){

        if (customersService.findAll().size() == 1){
            LOGGER.info(customersService.findAll().size() + " customer found");
        }
        if (customersService.findAll().size() > 1){
            LOGGER.info(customersService.findAll().size() + " customers found");
        }

        return customersService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Customers> add(@RequestBody Customers customers){

        if(customers.getCustomerId() != null && customers.getCustomerId() != 0){
            LOGGER.error("Redundand param: id must be null");
            return new ResponseEntity("Redundand param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustFirstName() == null || customers.getCustFirstName().trim().length() == 0){
            LOGGER.error("Missed param: custFirstName");
            return new ResponseEntity("Missed param: custFirstName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustLastName() == null || customers.getCustLastName().trim().length() == 0){
            LOGGER.error("Missed param: custLastName");
            return new ResponseEntity("Missed param: custLastName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getPhoneNumber() == null || customers.getPhoneNumber().trim().length() == 0){
            LOGGER.error("Missed param: phoneNumber");
            return new ResponseEntity("Missed param: phoneNumber", HttpStatus.NOT_ACCEPTABLE);
        }

        if(!customers.getPhoneNumber().matches("-?[\\d]+")){
            LOGGER.error("CustomerPhone can contain only numbers");
            return new ResponseEntity("CustomerPhone can contain only numbers", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustEmail() == null || customers.getCustEmail().trim().length() == 0){
            LOGGER.error("Missed param: custEmail");
            return new ResponseEntity("Missed param: custEmail", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Added customer: " + customers);

        return ResponseEntity.ok(customersService.add(customers));
    }

    @PutMapping("/update")
    public ResponseEntity<Customers> update(@RequestBody Customers customers){

        if(customers.getCustomerId() == null && customers.getCustomerId() == 0){
            LOGGER.error("Missed param: id");
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustFirstName() == null || customers.getCustFirstName().trim().length() == 0){
            LOGGER.error("Missed param: custFirstName");
            return new ResponseEntity("Missed param: custFirstName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustLastName() == null || customers.getCustLastName().trim().length() == 0){
            LOGGER.error("Missed param: custLastName");
            return new ResponseEntity("Missed param: custLastName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getPhoneNumber() == null || customers.getPhoneNumber().trim().length() == 0){
            LOGGER.error("Missed param: phoneNumber");
            return new ResponseEntity("Missed param: phoneNumber", HttpStatus.NOT_ACCEPTABLE);
        }

        if(!customers.getPhoneNumber().matches("-?[\\d]+")){
            LOGGER.error("CustomerPhone can contain only numbers");
            return new ResponseEntity("CustomerPhone can contain only numbers", HttpStatus.NOT_ACCEPTABLE);
        }

        if(customers.getCustEmail() == null || customers.getCustEmail().trim().length() == 0){
            LOGGER.error("Missed param: custEmail");
            return new ResponseEntity("Missed param: custEmail", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Updated customer: " + customers);

        return ResponseEntity.ok(customersService.update(customers));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Customers> findById(@PathVariable Long id){

        Customers customers = null;

        try {
            customers = customersService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Customer " + customers + " found");

        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            customersService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

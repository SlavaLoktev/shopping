package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Customers;
import com.shopping.shopping.service.CustomersService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomersController extends AbstractController<Customers, CustomersService>{

    private static final Logger LOGGER = Logger.getLogger(CustomersController.class);

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        super(customersService);
        this.customersService = customersService;
    }

    public boolean checkAdditionalParams(Customers customers){

        if(customers.getCustFirstName() == null || customers.getCustFirstName().trim().length() == 0){
            LOGGER.error("Missed param: custFirstName");
            return false;
        }

        if(customers.getCustLastName() == null || customers.getCustLastName().trim().length() == 0){
            LOGGER.error("Missed param: custLastName");
            return false;
        }

        if(customers.getPhoneNumber() == null || customers.getPhoneNumber().trim().length() == 0){
            LOGGER.error("Missed param: phoneNumber");
            return false;
        }

        if(!customers.getPhoneNumber().matches("-?[\\d]+")){
            LOGGER.error("CustomerPhone can contain only numbers");
            return false;
        }

        if(customers.getCustEmail() == null || customers.getCustEmail().trim().length() == 0){
            LOGGER.error("Missed param: custEmail");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody Customers customers, String operationType){

        switch (operationType){
            case "add":
                if(customers.getCustomerId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(customers)){
                    return false;
                }
                break;
            case "update":
                if(customers.getCustomerId() == null || customers.getCustomerId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(customers)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }
}

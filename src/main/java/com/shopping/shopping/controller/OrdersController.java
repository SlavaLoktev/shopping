package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Orders;
import com.shopping.shopping.service.OrdersService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdersController extends AbstractController<Orders, OrdersService>{

    private static final Logger LOGGER = Logger.getLogger(OrdersController.class);

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        super(ordersService);
        this.ordersService = ordersService;
    }

    public boolean checkAdditionalParams(Orders orders){

        if (orders.getCustomerFirstName() == null || orders.getCustomerFirstName().trim().length() == 0){
            LOGGER.error("Missed param: customerFirstName");
            return false;
        }

        if (orders.getCustomerLastName() == null || orders.getCustomerLastName().trim().length() == 0){
            LOGGER.error("Missed param: customerLastName");
            return false;
        }

        if (orders.getCustomerAddress() == null || orders.getCustomerAddress().trim().length() == 0){
            LOGGER.error("Missed param: customerAddress");
            return false;
        }

        if (orders.getCustomerEmail() == null || orders.getCustomerEmail().trim().length() == 0){
            LOGGER.error("Missed param: customerEmail");
            return false;
        }

        if (orders.getCustomerPhone() == null || orders.getCustomerPhone().trim().length() == 0){
            LOGGER.error("Missed param: customerPhone");
            return false;
        }

        if(!orders.getCustomerPhone().matches("-?[\\d]+")){
            LOGGER.error("CustomerPhone can contain only numbers");
            return false;
        }

        if(orders.getQuantity() < 0 || orders.getQuantity() == 0 || orders.getQuantity() == null){
            LOGGER.error("Quantity couldn't be <= 0");
            return false;
        }

        if(orders.getOrderDate() == null){
            LOGGER.error("Date can not be null");
            return false;
        }

        if(orders.getOrderTotal() == null || orders.getOrderTotal() == 0){
            LOGGER.error("Missed param: orderTotal");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody Orders orders, String operationType){

        orders.getQuantity().intValue();

        switch (operationType){
            case "add":
                if(orders.getOrderId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(orders)){
                    return false;
                }
                break;
            case "update":
                if(orders.getOrderId() == null || orders.getOrderId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(orders)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }
}

package com.shopping.shopping.controller;

import com.shopping.shopping.entity.OrderDetails;
import com.shopping.shopping.service.OrderDetailsService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderDetailsController extends AbstractController<OrderDetails, OrderDetailsService>{

    private static final Logger LOGGER = Logger.getLogger(OrderDetailsController.class);

    private final OrderDetailsService orderDetailsService;

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        super(orderDetailsService);
        this.orderDetailsService = orderDetailsService;
    }

    public boolean checkAdditionalParams(OrderDetails orderDetails){

        if (orderDetails.getQuantity() < 0 || orderDetails.getQuantity() == 0 || orderDetails.getQuantity() == null){
            LOGGER.error("Quantity couldn't be <= 0");
            return false;
        }

        if (orderDetails.getProduct().getProductId() == 0 || orderDetails.getProduct().getProductId() == null){
            LOGGER.error("Product doesn't exist");
            return false;
        }

        if (orderDetails.getQuantity() != (int)orderDetails.getQuantity()){
            LOGGER.error("Quantity must be integer");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody OrderDetails orderDetails, String operationType){

        switch (operationType){
            case "add":
                if(orderDetails.getOrderDetailId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(orderDetails)){
                    return false;
                }
                break;
            case "update":
                if(orderDetails.getOrderDetailId() == null || orderDetails.getOrderDetailId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(orderDetails)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }
}

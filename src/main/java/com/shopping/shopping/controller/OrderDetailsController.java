package com.shopping.shopping.controller;

import com.shopping.shopping.entity.OrderDetails;
import com.shopping.shopping.service.OrderDetailsService;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orderDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    private static Logger LOGGER = Logger.getLogger(ProductController.class);

    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/all")
    public List<OrderDetails> findAll(){

        if (orderDetailsService.findAll().size() == 1){
            LOGGER.info(orderDetailsService.findAll().size() + " orderDetail found");
        }
        if (orderDetailsService.findAll().size() > 1){
            LOGGER.info(orderDetailsService.findAll().size() + " orderDetails found");
        }

        return orderDetailsService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDetails> add(@RequestBody OrderDetails orderDetails){

        if(orderDetails.getOrderDetailId() != null && orderDetails.getOrderDetailId()  != 0){
            LOGGER.error("Redundand param: id must be null");
            return new ResponseEntity("Redundand param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orderDetails.getQuantity() < 0 || orderDetails.getQuantity() == 0 || orderDetails.getQuantity() == null){
            LOGGER.error("Quantity couldn't be <= 0");
            return new ResponseEntity("Quantity can not be <= 0", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orderDetails.getProduct().getProductId() == 0 || orderDetails.getProduct().getProductId() == null){
            LOGGER.error("Product doesn't exist");
            return new ResponseEntity("Product doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }

        orderDetails.getQuantity().intValue();

        LOGGER.info("Added orderDetails: " + orderDetails);

        return ResponseEntity.ok(orderDetailsService.add(orderDetails));
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDetails> update(@RequestBody OrderDetails orderDetails){

        if(orderDetails.getOrderDetailId() == null && orderDetails.getOrderDetailId()  == 0){
            LOGGER.error("Missed param: id");
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orderDetails.getQuantity() < 0 || orderDetails.getQuantity() == 0 || orderDetails.getQuantity() == null){
            LOGGER.error("Quantity couldn't be <= 0");
            return new ResponseEntity("Quantity can not be <= 0", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orderDetails.getProduct().getProductId() == 0 || orderDetails.getProduct().getProductId() == null){
            LOGGER.error("Product doesn't exist");
            return new ResponseEntity("Product doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orderDetails.getOrder().getOrderId() == 0 || orderDetails.getOrder().getOrderId() == null){
            LOGGER.error("Order doesn't exist");
            return new ResponseEntity("Order doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }

        orderDetails.getQuantity().intValue();

        LOGGER.info("Updated orderDetails: " + orderDetails);

        return ResponseEntity.ok(orderDetailsService.update(orderDetails));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrderDetails> findById(@PathVariable Long id){

        OrderDetails orderDetails = null;

        try {
            orderDetails = orderDetailsService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("OrderDetails with id: " + id + " found");

        return ResponseEntity.ok(orderDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            orderDetailsService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Deleted orderDetails with id: " + id);

        return new ResponseEntity(HttpStatus.OK);
    }
}

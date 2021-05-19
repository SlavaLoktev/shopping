package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Orders;
import com.shopping.shopping.service.OrdersService;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdersController {

    private final OrdersService ordersService;

    private static Logger LOGGER = Logger.getLogger(ProductController.class);

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/all")
    public List<Orders> findAll(){

        if (ordersService.findAll().size() == 1){
            LOGGER.info(ordersService.findAll().size() + " order found");
        }
        if (ordersService.findAll().size() > 1){
            LOGGER.info(ordersService.findAll().size() + " orders found");
        }

        return ordersService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Orders> add(@RequestBody Orders orders){

        if(orders.getOrderId() != null && orders.getOrderId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            LOGGER.error("Redundand param: id must be null");
            return new ResponseEntity("Redundand param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerFirstName() == null || orders.getCustomerFirstName().trim().length() == 0){
            LOGGER.error("Missed param: customerFirstName");
            return new ResponseEntity("Missed param: customerFirstName", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerLastName() == null || orders.getCustomerLastName().trim().length() == 0){
            LOGGER.error("Missed param: customerLastName");
            return new ResponseEntity("Missed param: customerLastName", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerAddress() == null || orders.getCustomerAddress().trim().length() == 0){
            LOGGER.error("Missed param: customerAddress");
            return new ResponseEntity("Missed param: customerAddress", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerEmail() == null || orders.getCustomerEmail().trim().length() == 0){
            LOGGER.error("Missed param: customerEmail");
            return new ResponseEntity("Missed param: customerEmail", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerPhone() == null || orders.getCustomerPhone().trim().length() == 0){
            LOGGER.error("Missed param: customerPhone");
            return new ResponseEntity("Missed param: customerPhone", HttpStatus.NOT_ACCEPTABLE);
        }

        if(orders.getQuantity() < 0 || orders.getQuantity() == 0 || orders.getQuantity() == null){
            LOGGER.error("Quantity couldn't be <= 0");
            return new ResponseEntity("Quantity can not be <= 0", HttpStatus.NOT_ACCEPTABLE);
        }

        if(orders.getOrderDate() == null){
            LOGGER.error("Date can not be null");
            return new ResponseEntity("Date can not be null", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Added order: " + orders);

        return ResponseEntity.ok(ordersService.add(orders));
    }

    @PutMapping("/update")
    public ResponseEntity<Orders> update(@RequestBody Orders orders){

        if(orders.getOrderId() == null && orders.getOrderId() == 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            LOGGER.error("Missed param: id");
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerFirstName() == null || orders.getCustomerFirstName().trim().length() == 0){
            LOGGER.error("Missed param: customerFirstName");
            return new ResponseEntity("Missed param: customerFirstName", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerLastName() == null || orders.getCustomerLastName().trim().length() == 0){
            LOGGER.error("Missed param: customerLastName");
            return new ResponseEntity("Missed param: customerLastName", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerAddress() == null || orders.getCustomerAddress().trim().length() == 0){
            LOGGER.error("Missed param: customerAddress");
            return new ResponseEntity("Missed param: customerAddress", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerEmail() == null || orders.getCustomerEmail().trim().length() == 0){
            LOGGER.error("Missed param: customerEmail");
            return new ResponseEntity("Missed param: customerEmail", HttpStatus.NOT_ACCEPTABLE);
        }

        if (orders.getCustomerPhone() == null || orders.getCustomerPhone().trim().length() == 0){
            LOGGER.error("Missed param: customerPhone");
            return new ResponseEntity("Missed param: customerPhone", HttpStatus.NOT_ACCEPTABLE);
        }

        if(orders.getQuantity() < 0 || orders.getQuantity() == 0 || orders.getQuantity() == null){
            LOGGER.error("Quantity couldn't be <= 0");
            return new ResponseEntity("Quantity can not be <= 0", HttpStatus.NOT_ACCEPTABLE);
        }

        if(orders.getOrderDate() == null){
            LOGGER.error("Date can not be null");
            return new ResponseEntity("Date can not be null", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Updated order: " + orders);

        return ResponseEntity.ok(ordersService.update(orders));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Orders> findById(@PathVariable Long id){

        Orders orders = null;

        try {
            orders = ordersService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Order with id: " + id + " found");

        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            ordersService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Deleted order with id: " + id);

        return new ResponseEntity(HttpStatus.OK);
    }
}

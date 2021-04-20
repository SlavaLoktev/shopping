package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Product;
import com.shopping.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// используем @RestController, чтобы все отвыеты сразу оборачивались в JSON
@RestController
@RequestMapping("/product")
public class ProductController {

    //@Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //для тестирования адрес: http://localhost:8080/product/test
    @GetMapping("/test")
    public List<Product> test(){

        List<Product> list = productRepository.findAll();

        return list;
    }

    @PostMapping("/add")
    public void add(@RequestBody Product product){
        productRepository.save(product);
    }
}

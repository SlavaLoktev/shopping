package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Product;
import com.shopping.shopping.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// используем @RestController, чтобы все отвыеты сразу оборачивались в JSON
@RestController
@RequestMapping("/product")//базовый адрес
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //для тестирования адрес: http://localhost:8080/shopping/product/test
    @ResponseBody
    @GetMapping("/test")
    public List<Product> test(){

        List<Product> list = productRepository.findAll();

        return list;
    }
}

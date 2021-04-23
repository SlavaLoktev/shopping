package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Product;
import com.shopping.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// используем @RestController, чтобы все отвыеты сразу оборачивались в JSON
@RestController
@RequestMapping("/product")
public class ProductController {

    //@Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //для тестирования адрес: http://localhost:8080/product/all
    @GetMapping("/all")
    public List<Product> findAll(){

        return productRepository.findAll();
    }

    @GetMapping("/allByOrderByPriceAsc")
    public List<Product> findAllByOrderByPriceAsc(){

        return productRepository.findAllByOrderByPriceAsc();
    }

    @GetMapping("/allByOrderByPriceDesc")
    public List<Product> findAllByOrderByPriceDesc(){

        return productRepository.findAllByOrderByPriceDesc();
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody Product product){

        //проверка на обязательные параметры
        if(product.getProductId() != null && product.getProductId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundand param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение product_name
        if(product.getProductName() == null || product.getProductName().trim().length() == 0){
            return new ResponseEntity("missed param: productName", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение price
        if(product.getPrice() == null || product.getPrice() == 0){
            return new ResponseEntity("missed param: price", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение storage_unit
        if(product.getStorageUnit() == null || product.getProductName().trim().length() == 0){
            return new ResponseEntity("missed param: storageUnit", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(productRepository.save(product));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Product product){

        if(product.getProductId() == null && product.getProductId() == 0){
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение product_name
        if(product.getProductName() == null || product.getProductName().trim().length() == 0){
            return new ResponseEntity("missed param: productName", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение price
        if(product.getPrice() == null || product.getPrice() == 0){
            return new ResponseEntity("missed param: price", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение storage_unit
        if(product.getStorageUnit() == null || product.getProductName().trim().length() == 0){
            return new ResponseEntity("missed param: storageUnit", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(productRepository.save(product));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){

        Product product = null;

        try {
            product = productRepository.findById(id).get();
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            productRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

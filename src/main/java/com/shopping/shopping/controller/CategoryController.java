package com.shopping.shopping.controller;


import com.shopping.shopping.entity.Category;
import com.shopping.shopping.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/test")
    public List<Category> test(){

        List<Category> list = categoryRepository.findAll();

        return list;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){

        if(category.getCategoryId() != null && category.getCategoryId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение categoryName
        if(category.getCategoryName() == null || category.getCategoryName().trim().length() == 0){
            return new ResponseEntity("missed param: categoryName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @PutMapping("update")
    public ResponseEntity update(@RequestBody Category category) {

        //проверка на обязательные параметры
        if (category.getCategoryId() == null && category.getCategoryId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение categoryName
        if (category.getCategoryName() == null || category.getCategoryName().trim().length() == 0) {
            return new ResponseEntity("missed param: categoryName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }
}

package com.shopping.shopping.controller;


import com.shopping.shopping.entity.Category;
import com.shopping.shopping.repository.CategoryRepository;
import com.shopping.shopping.service.CategoryService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> findAll(){

        return categoryService.findAll();
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

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        //проверка на обязательные параметры
        if (category.getCategoryId() == null && category.getCategoryId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение categoryName
        if (category.getCategoryName() == null || category.getCategoryName().trim().length() == 0) {
            return new ResponseEntity("missed param: categoryName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){

        Category category = null;

        try {
            category = categoryService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            categoryService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

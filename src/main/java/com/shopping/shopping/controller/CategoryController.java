package com.shopping.shopping.controller;


import com.shopping.shopping.entity.Category;
import com.shopping.shopping.search.CategorySearchValues;
import com.shopping.shopping.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    private static Logger LOGGER = Logger.getLogger(ProductController.class);

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public List<Category> findAll(){

        if (categoryService.findAll().size() == 1){
            LOGGER.info(categoryService.findAll().size() + " category found");
        }
        if (categoryService.findAll().size() > 1){
            LOGGER.info(categoryService.findAll().size() + " categories found");
        }

        return categoryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){

        if(category.getCategoryId() != null && category.getCategoryId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            LOGGER.error("Redundand param: id must be null");
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение categoryName
        if(category.getCategoryName() == null || category.getCategoryName().trim().length() == 0){
            LOGGER.error("Missed param: categoryName");
            return new ResponseEntity("Missed param: categoryName", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Added category: " + category);

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        //проверка на обязательные параметры
        if (category.getCategoryId() == null && category.getCategoryId() == 0) {
            LOGGER.error("Missed param: id");
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение categoryName
        if (category.getCategoryName() == null || category.getCategoryName().trim().length() == 0) {
            LOGGER.error("Missed param: categoryName");
            return new ResponseEntity("Missed param: categoryName", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Updated category: " + category);

        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){

        Category category = null;

        try {
            category = categoryService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Category " + category + " found");

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            categoryService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Deleted category with id: " + id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Category>> search(@RequestBody CategorySearchValues categorySearchValues){

        Long departmentId = categorySearchValues.getDepartmentId() != null ? categorySearchValues.getDepartmentId() : null;

        List<Category> result = categoryService.findByParams(departmentId);

        if (result.size() == 1){
            LOGGER.info(result.size() + " category found");
        }

        if (result.size() > 1){
            LOGGER.info(result.size() + " categories found");
        }

        return ResponseEntity.ok(result);
    }
}

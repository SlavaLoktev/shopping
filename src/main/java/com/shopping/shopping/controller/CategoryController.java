package com.shopping.shopping.controller;


import com.shopping.shopping.entity.Category;
import com.shopping.shopping.search.CategorySearchValues;
import com.shopping.shopping.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController extends AbstractController<Category, CategoryService>{

    private static final Logger LOGGER = Logger.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

    public boolean checkAdditionalParams(Category category){

        if(category.getCategoryName() == null || category.getCategoryName().trim().length() == 0){
            LOGGER.error("Missed param: categoryName");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody Category category, String operationType){

        switch (operationType){
            case "add":
                if(category.getCategoryId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(category)){
                    return false;
                }
                break;
            case "update":
                if(category.getCategoryId() == null || category.getCategoryId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(category)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
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

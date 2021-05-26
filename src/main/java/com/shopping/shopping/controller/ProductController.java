package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Product;
import com.shopping.shopping.search.ProductSearchValues;
import com.shopping.shopping.search.ProductSearchValuesWithoutPaging;
import com.shopping.shopping.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController extends AbstractController<Product, ProductService>{

    private static final Logger LOGGER = Logger.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        super(productService);
        this.productService = productService;
    }

    public boolean checkAdditionalParams(Product product){

        if(product.getProductName() == null || product.getProductName().trim().length() == 0){
            LOGGER.error("Missed param: productName");
            return false;
        }

        if(product.getPrice() == null || product.getPrice() == 0){
            LOGGER.error("Missed param: price");
            return false;
        }

        if(product.getStorageUnit() == null || product.getStorageUnit().trim().length() == 0){
            LOGGER.error("Missed param: storageUnit");
            return false;
        }

        if(product.getPrice() < 0){
            LOGGER.error("Price couldn't be < 0");
            return false;
        }

        if(product.getDiscountPrice() < 0){
            LOGGER.error("Price couldn't be < 0");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody Product product, String operationType){

        switch (operationType){
            case "add":
                if(product.getProductId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(product)){
                    return false;
                }
                break;
            case "update":
                if(product.getProductId() == null || product.getProductId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(product)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }

    @GetMapping("/allByOrderByPriceAsc")
    public ResponseEntity<List<Product>> findAllByOrderByPriceAsc(){

        List<Product> result = null;

        try {
            result = productService.findAllByOrderByPriceAsc();
        }catch (EmptyResultDataAccessException e){
            LOGGER.error(String.format("%s, product(s) can not found", HttpStatus.NOT_FOUND));
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/allByOrderByPriceDesc")
    public ResponseEntity<List<Product>> findAllByOrderByPriceDesc(){

        List<Product> result = null;

        try {
            result = productService.findAllByOrderByPriceDesc();
        }catch (EmptyResultDataAccessException e){
            LOGGER.error(String.format("%s, product(s) can not found", HttpStatus.NOT_FOUND));
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Product>> search(@RequestBody ProductSearchValues productSearchValues){

        String productName = productSearchValues.getProductName() != null ? productSearchValues.getProductName() : null;

        Integer price = productSearchValues.getPrice() != null ? productSearchValues.getPrice() : null;

        String sortColumn = productSearchValues.getSortColumn() != null ? productSearchValues.getSortColumn() : null;
        String sortDirection = productSearchValues.getSortDirection() != null ? productSearchValues.getSortDirection() : null;

        Integer pageNumber = productSearchValues.getPageNumber() != null ? productSearchValues.getPageNumber() : null;
        Integer pageSize = productSearchValues.getPageSize() != null ? productSearchValues.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page result = null;

        try {
            result = productService.findByParams(productName, price, pageRequest);
        }catch (EmptyResultDataAccessException e){
            LOGGER.error(String.format("%s, product(s) can not found", HttpStatus.NOT_FOUND));
        }

        LOGGER.info(String.format("%d product(s) found", result.getSize()));

        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchWithoutPaging")
    public ResponseEntity<List<Product>> searchWithoutPaging(@RequestBody ProductSearchValuesWithoutPaging productSearchValuesWithoutPaging){

        String productName = productSearchValuesWithoutPaging.getProductName() != null ? productSearchValuesWithoutPaging.getProductName() : null;

        Integer price = productSearchValuesWithoutPaging.getPrice() != null ? productSearchValuesWithoutPaging.getPrice() : null;

        Long categoryId = productSearchValuesWithoutPaging.getCategoryId() != null ? productSearchValuesWithoutPaging.getCategoryId() : null;

        Long departmentId = productSearchValuesWithoutPaging.getDepartmentId() != null ? productSearchValuesWithoutPaging.getDepartmentId() : null;

        List<Product> result = null;

        try {
            result = productService.findByParamsWithoutPaging(productName, price, categoryId, departmentId);
        }catch (EmptyResultDataAccessException e){
            LOGGER.error(String.format("%s, product(s) can not found", HttpStatus.NOT_FOUND));
        }

        LOGGER.info(String.format("%d product(s) found", result.size()));

        return ResponseEntity.ok(result);
    }
}

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
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    private static Logger LOGGER = Logger.getLogger(ProductController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //для тестирования адрес: http://localhost:8080/product/all
    @GetMapping("/all")
    public List<Product> findAll(){

        if (productService.findAll().size() == 1){
            LOGGER.info(productService.findAll().size() + " product found");
        }
        if (productService.findAll().size() > 1){
            LOGGER.info(productService.findAll().size() + " products found");
        }

        return productService.findAll();
    }

    @GetMapping("/allByOrderByPriceAsc")
    public List<Product> findAllByOrderByPriceAsc(){

        if (productService.findAllByOrderByPriceAsc().size() == 1){
            LOGGER.info(productService.findAll().size() + " product found");
        }
        if (productService.findAllByOrderByPriceAsc().size() > 1){
            LOGGER.info(productService.findAll().size() + " products found");
        }

        return productService.findAllByOrderByPriceAsc();
    }

    @GetMapping("/allByOrderByPriceDesc")
    public List<Product> findAllByOrderByPriceDesc(){

        if (productService.findAllByOrderByPriceDesc().size() == 1){
            LOGGER.info(productService.findAll().size() + " product found");
        }
        if (productService.findAllByOrderByPriceDesc().size() > 1){
            LOGGER.info(productService.findAll().size() + " products found");
        }

        return productService.findAllByOrderByPriceDesc();
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody Product product){

        if(product.getProductId() != null && product.getProductId() != 0){
            LOGGER.error("Redundand param: id must be null");
            return new ResponseEntity("Redundand param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getProductName() == null || product.getProductName().trim().length() == 0){
            LOGGER.error("Missed param: productName");
            return new ResponseEntity("Missed param: productName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getPrice() == null || product.getPrice() == 0){
            LOGGER.error("Missed param: price");
            return new ResponseEntity("Missed param: price", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getStorageUnit() == null || product.getStorageUnit().trim().length() == 0){
            LOGGER.error("Missed param: storageUnit");
            return new ResponseEntity("Missed param: storageUnit", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getPrice() < 0){
            LOGGER.error("Price couldn't be < 0");
            return new ResponseEntity("Price can not be < 0", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getDiscountPrice() < 0){
            LOGGER.error("Price couldn't be < 0");
            return new ResponseEntity("Price can not be < 0", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Added product: " + product);

        return ResponseEntity.ok(productService.add(product));
    }

    @PutMapping("/update")
    public ResponseEntity<Product> update(@RequestBody Product product){

        if(product.getProductId() == null && product.getProductId() == 0){
            LOGGER.error("Missed param: id");
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getProductName() == null || product.getProductName().trim().length() == 0){
            LOGGER.error("Missed param: productName");
            return new ResponseEntity("Missed param: productName", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getPrice() == null || product.getPrice() == 0){
            LOGGER.error("Missed param: price");
            return new ResponseEntity("Missed param: price", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getStorageUnit() == null || product.getStorageUnit().trim().length() == 0){
            LOGGER.error("Missed param: storageUnit");
            return new ResponseEntity("Missed param: storageUnit", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getPrice() < 0){
            LOGGER.error("Price couldn't be < 0");
            return new ResponseEntity("Price can not < 0", HttpStatus.NOT_ACCEPTABLE);
        }

        if(product.getDiscountPrice() < 0){
            LOGGER.error("Price couldn't be < 0");
            return new ResponseEntity("Price can not be < 0", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Updated product: " + product);

        return ResponseEntity.ok(productService.update(product));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){

        Product product = null;

        try {
            product = productService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Product " + product + " found");

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            productService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Deleted product with id: " + id);

        return new ResponseEntity(HttpStatus.OK);
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

        Page result = productService.findByParams(productName, price, pageRequest);

        if (result.getSize() == 1){
            LOGGER.info(result.getSize() + " product found");
        }
        if (result.getSize() > 1){
            LOGGER.info(result.getSize() + " products found");
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/searchWithoutPaging")
    public ResponseEntity<List<Product>> searchWithoutPaging(@RequestBody ProductSearchValuesWithoutPaging productSearchValuesWithoutPaging){

        String productName = productSearchValuesWithoutPaging.getProductName() != null ? productSearchValuesWithoutPaging.getProductName() : null;

        Integer price = productSearchValuesWithoutPaging.getPrice() != null ? productSearchValuesWithoutPaging.getPrice() : null;

        List<Product> result = productService.findByParamsWithoutPaging(productName, price);

        if (result.size() == 1){
            LOGGER.info(result.size() + " product found");
        }

        if (result.size() > 1){
            LOGGER.info(result.size() + " products found");
        }

        return ResponseEntity.ok(result);
    }
}

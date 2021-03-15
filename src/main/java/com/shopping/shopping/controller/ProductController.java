package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Product;
import com.shopping.shopping.search.ProductSearchValues;
import com.shopping.shopping.service.ProductService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

// используем @RestController, чтобы все отвыеты сразу оборачивались в JSON
@RestController
@RequestMapping("/product")//базовый адрес
public class ProductController {

    //Доступ к данным из БД
    //private ProductRepository productRepository;
    private ProductService productService;

    //автоматическое внедрение экземпляра класса через конструктор
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //для тестирования адрес: http://localhost:8080/product/all
    @GetMapping("/all")
    public List<Product> findAll(){

        return productService.findAll();
    }

    @GetMapping("/allByOrderByPriceAsc")
    public List<Product> findAllByOrderByPriceAsc(){

        return productService.findAllByOrderByPriceAsc();
    }

    @GetMapping("/allByOrderByPriceDesc")
    public List<Product> findAllByOrderByPriceDesc(){

        return productService.findAllByOrderByPriceDesc();
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody Product product){

        //проверка на обязательные параметры
        if(product.getProductId() != null && product.getProductId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение product_name
        if(product.getProductName() == null || product.getProductName().trim().length() == 0){//имеет какие то символы
            return new ResponseEntity("missed param: product_name", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение price
        if(product.getPrice() == null || product.getPrice() != 0){
            return new ResponseEntity("missed param: price", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение storage_unit
        if(product.getStorageUnit() == null || product.getProductName().trim().length() == 0){
            return new ResponseEntity("missed param: storage_unit", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(productService.add(product));//добавляем и сразу получаем объект и можем с ним работать
    }

    @PutMapping("update")
    public ResponseEntity<Product> update(@RequestBody Product product){

        //проверка на обязательные параметры
        if(product.getProductId() == null && product.getProductId() == 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение product_name
        if(product.getProductName() == null || product.getProductName().trim().length() == 0){
            return new ResponseEntity("missed param: product_name", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение price
        if(product.getPrice() == null || product.getPrice() != 0){
            return new ResponseEntity("missed param: price", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение storage_unit
        if(product.getStorageUnit() == null || product.getStorageUnit().trim().length() == 0){
            return new ResponseEntity("missed param: storage_unit", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(productService.update(product));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id){

        Product product = null;

        try {
            product = productService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            productService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    //поиск по любым параметрам ProductSearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<Product>> search(@RequestBody ProductSearchValues productSearchValues){


        //исключить NullPointerException
        String text = productSearchValues.getProductName() != null ? productSearchValues.getProductName() : null;

        String sortColumn = productSearchValues.getSortColumn() != null ? productSearchValues.getSortColumn() : null;
        String sortDirection = productSearchValues.getSortDirection() != null ? productSearchValues.getSortDirection() : null;

        Integer pageNumber = productSearchValues.getPageNumber() != null ? productSearchValues.getPageNumber() : null;
        Integer pageSize = productSearchValues.getPageSize() != null ? productSearchValues.getPageSize() : null;

        //если null или пустой - используем asc, иначе desc
        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        //подставляем все значения-------------

        //объект сортировки
        Sort sort = Sort.by(direction, sortColumn);

        //объект постраничности
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        //результат запроса с постраничным выводом
        Page result = productService.findByParams(text, pageRequest);

        //результат запроса
        return ResponseEntity.ok(result);
    }

}

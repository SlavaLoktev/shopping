package com.shopping.shopping.service;

import com.shopping.shopping.controller.ProductController;
import com.shopping.shopping.entity.Product;
import com.shopping.shopping.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
// все методы класса должны выполниться без ошибки, чтобы транзакция завершилась
// если в методе возникнет исключение - все выполненные операции откатятся (Rollback)
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product add(Product product){
        return repository.save(product);//метод save обновляет или создает новый объект, если его не было
    }

    public Product update(Product product){
        return repository.save(product);//метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Page findByParams(String productName, Integer price, PageRequest paging){
        return repository.findByParams(productName, price, paging);
    }

    public Product findById(Long id){
        return repository.findById(id).get();// т.к. возвращается optional - нужно  получить объект методом get()
    }

    public List<Product> findAllByOrderByPriceAsc(){
        return repository.findAllByOrderByPriceAsc();
    }

    public List<Product> findAllByOrderByPriceDesc(){
        return repository.findAllByOrderByPriceDesc();
    }

    public List<Product> findByParamsWithoutPaging(String productName, Integer price){
        return repository.findByParamsWithoutPaging(productName, price);
    }
}

package com.shopping.shopping.service;

import com.shopping.shopping.entity.Product;
import com.shopping.shopping.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService extends AbstractService<Product, ProductRepository>{

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Page findByParams(String productName, Integer price, PageRequest paging){
        return repository.findByParams(productName, price, paging);
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

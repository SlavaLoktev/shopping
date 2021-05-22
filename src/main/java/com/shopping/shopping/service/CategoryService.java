package com.shopping.shopping.service;

import com.shopping.shopping.entity.Category;
import com.shopping.shopping.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService extends AbstractService<Category, CategoryRepository>{

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Category> findByParams(Long departmentId){
        return repository.findByParams(departmentId);
    }
}

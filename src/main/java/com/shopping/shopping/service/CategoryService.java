package com.shopping.shopping.service;

import com.shopping.shopping.entity.Category;
import com.shopping.shopping.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category add(Category category){
        return repository.save(category);//метод save обновляет или создает новый объект, если его не было
    }

    public Category update(Category category){
        return repository.save(category);//метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Category findById(Long id){
        return repository.findById(id).get();// т.к. возвращается optional - нужно  получить объект методом get()
    }

    public List<Category> findByParams(Long departmentId){
        return repository.findByParams(departmentId);
    }
}

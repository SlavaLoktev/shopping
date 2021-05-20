package com.shopping.shopping.service;

import com.shopping.shopping.entity.Department;
import com.shopping.shopping.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<Department> findAll(){
        return repository.findAll();
    }

    public Department add(Department department){
        return repository.save(department);
    }

    public Department update(Department department){
        return repository.save(department);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Department findById(Long id){
        return repository.findById(id).get();
    }
}

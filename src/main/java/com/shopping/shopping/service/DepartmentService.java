package com.shopping.shopping.service;

import com.shopping.shopping.entity.Department;
import com.shopping.shopping.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DepartmentService extends AbstractService<Department, DepartmentRepository>{

    public DepartmentService(DepartmentRepository repository) {
        super(repository);
    }
}

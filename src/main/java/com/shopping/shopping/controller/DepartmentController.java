package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Department;
import com.shopping.shopping.repository.CategoryRepository;
import com.shopping.shopping.repository.DepartmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/test")
    public List<Department> test(){

        List<Department> list = departmentRepository.findAll();

        return list;
    }

    @PostMapping("/add")
    public void add(@RequestBody Department department){
        departmentRepository.save(department);
    }
}

package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Department;
import com.shopping.shopping.repository.CategoryRepository;
import com.shopping.shopping.repository.DepartmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Department> add(@RequestBody Department department){

        if(department.getDepartmentId() != null && department.getDepartmentId() != 0){
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение departmentName
        if(department.getDepartmentName() == null || department.getDepartmentName().trim().length() == 0){
            return new ResponseEntity("missed param: departmentName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(departmentRepository.save(department));
    }
}

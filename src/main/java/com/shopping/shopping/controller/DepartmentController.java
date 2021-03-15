package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Department;
import com.shopping.shopping.service.DepartmentService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //для тестирования адрес: http://localhost:8080/department/all
    @GetMapping("/all")
    public List<Department> findAll(){

        return departmentService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Department> add(@RequestBody Department department){

        //проверка на обязательные параметры
        if(department.getDepartmentId() != null && department.getDepartmentId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение department_name
        if(department.getDepartmentName() == null || department.getDepartmentName().trim().length() == 0){
            return new ResponseEntity("missed param: department_name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(departmentService.add(department));//добавляем и сразу получаем объект и можем с ним работать
    }

    @PutMapping("update")
    public ResponseEntity<Department> update(@RequestBody Department department){

        //проверка на обязательные параметры
        if(department.getDepartmentId() == null && department.getDepartmentId() == 0){
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение department_name
        if(department.getDepartmentName() == null || department.getDepartmentName().trim().length() == 0){
            return new ResponseEntity("missed param: department_name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(departmentService.update(department));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){

        Department department = null;

        try {
            department = departmentService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            departmentService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}

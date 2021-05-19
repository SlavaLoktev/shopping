package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Department;
import com.shopping.shopping.repository.CategoryRepository;
import com.shopping.shopping.repository.DepartmentRepository;
import com.shopping.shopping.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

    private final DepartmentService departmentService;

    private static Logger LOGGER = Logger.getLogger(ProductController.class);

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public List<Department> findAll(){

        if (departmentService.findAll().size() == 1){
            LOGGER.info(departmentService.findAll().size() + " department found");
        }
        if (departmentService.findAll().size() > 1){
            LOGGER.info(departmentService.findAll().size() + " departments found");
        }

        return departmentService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Department> add(@RequestBody Department department){

        if(department.getDepartmentId() != null && department.getDepartmentId() != 0){
            LOGGER.error("Redundand param: id must be null");
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение departmentName
        if(department.getDepartmentName() == null || department.getDepartmentName().trim().length() == 0){
            LOGGER.error("Missed param: departmentName");
            return new ResponseEntity("Missed param: departmentName", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Added department: " + department);

        return ResponseEntity.ok(departmentService.add(department));
    }

    @PutMapping("update")
    public ResponseEntity<Department> update(@RequestBody Department department) {

        //проверка на обязательные параметры
        if (department.getDepartmentId() == null && department.getDepartmentId() == 0) {
            LOGGER.error("Missed param: id");
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение departmentName
        if (department.getDepartmentName() == null || department.getDepartmentName().trim().length() == 0) {
            LOGGER.error("Missed param: departmentName");
            return new ResponseEntity("Missed param: departmentName", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Updated department: " + department);

        return ResponseEntity.ok(departmentService.update(department));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){

        Department department = null;

        try {
            department = departmentService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Department " + department + " found");

        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            departmentService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Deleted department with id: " + id);

        return new ResponseEntity(HttpStatus.OK);
    }
}

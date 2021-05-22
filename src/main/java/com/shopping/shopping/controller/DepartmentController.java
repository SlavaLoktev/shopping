package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Department;
import com.shopping.shopping.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController extends AbstractController<Department, DepartmentService>{

    private static final Logger LOGGER = Logger.getLogger(DepartmentController.class);

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        super(departmentService);
        this.departmentService = departmentService;
    }

    public boolean checkAdditionalParams(Department department){

        if(department.getDepartmentName() == null || department.getDepartmentName().trim().length() == 0){
            LOGGER.error("Missed param: departmentName");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody Department department, String operationType){

        switch (operationType){
            case "add":
                if(department.getDepartmentId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(department)){
                    return false;
                }
                break;
            case "update":
                if(department.getDepartmentId() == null || department.getDepartmentId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(department)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }
}

//package com.shopping.shopping.controller;
//
//
//import com.shopping.shopping.entity.Attribute;
//import com.shopping.shopping.entity.Department;
//
//import com.shopping.shopping.entity.Product;
//import com.shopping.shopping.entity.Reviews;
//import com.shopping.shopping.repository.AttributeRepository;
//import com.shopping.shopping.repository.DepartmentRepository;
//import com.shopping.shopping.repository.ProductRepository;
//import com.shopping.shopping.repository.ReviewsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//@Controller
//public class DirectController {
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private AttributeRepository attributeRepository;
//
//    @Autowired
//    private ReviewsRepository reviewsRepository;
//
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String directURL(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        List<Department> departmentList = departmentRepository.findAll();
//        List<Attribute> attributeList = attributeRepository.findAll();
//        List<Product> productList = productRepository.findAll();
//        List<Reviews> reviewsList = reviewsRepository.findAll();
//        return "ok";
//    }
//}

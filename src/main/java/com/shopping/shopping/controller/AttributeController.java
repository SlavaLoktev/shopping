package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Attribute;
import com.shopping.shopping.repository.AttributeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

    private AttributeRepository attributeRepository;

    public AttributeController(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @GetMapping("/test")
    public List<Attribute> test(){

        List<Attribute> list = attributeRepository.findAll();

        return list;
    }
}

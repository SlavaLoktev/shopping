package com.shopping.shopping.controller;

import com.shopping.shopping.entity.AttrValue;
import com.shopping.shopping.repository.AttrValueRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attrvalue")
public class AttrValueController {

    private AttrValueRepository attrValueRepository;

    public AttrValueController(AttrValueRepository attrValueRepository) {
        this.attrValueRepository = attrValueRepository;
    }

    @GetMapping("/test")
    public List<AttrValue> test(){

        List<AttrValue> list = attrValueRepository.findAll();

        return list;
    }
}

package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Attribute;
import com.shopping.shopping.repository.AttributeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<Attribute> add(@RequestBody Attribute attribute){

        if(attribute.getAttributeId() != null && attribute.getAttributeId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attribute_name
        if(attribute.getAttributeName() == null || attribute.getAttributeName().trim().length() == 0){
            return new ResponseEntity("missed param: attributeName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attributeRepository.save(attribute));
    }

    @PutMapping("update")
    public ResponseEntity update(@RequestBody Attribute attribute) {

        //проверка на обязательные параметры
        if (attribute.getAttributeId() == null && attribute.getAttributeId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attributeName
        if (attribute.getAttributeName() == null || attribute.getAttributeName().trim().length() == 0) {
            return new ResponseEntity("missed param: attributeName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attributeRepository.save(attribute));
    }
}

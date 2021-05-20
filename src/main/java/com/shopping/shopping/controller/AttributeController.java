package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Attribute;
import com.shopping.shopping.service.AttributeService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/attribute")
@CrossOrigin(origins = "http://localhost:4200")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping("/all")
    public List<Attribute> findAll(){

        return attributeService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Attribute> add(@RequestBody Attribute attribute){

        if(attribute.getAttributeId() != null && attribute.getAttributeId() != 0){
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(attribute.getAttributeName() == null || attribute.getAttributeName().trim().length() == 0){
            return new ResponseEntity("missed param: attributeName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attributeService.add(attribute));
    }

    @PutMapping("update")
    public ResponseEntity<Attribute> update(@RequestBody Attribute attribute) {

        if (attribute.getAttributeId() == null && attribute.getAttributeId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (attribute.getAttributeName() == null || attribute.getAttributeName().trim().length() == 0) {
            return new ResponseEntity("missed param: attributeName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attributeService.update(attribute));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Attribute> findById(@PathVariable Long id){

        Attribute attribute = null;

        try {
            attribute = attributeService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attribute);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            attributeService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

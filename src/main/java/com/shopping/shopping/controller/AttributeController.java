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
public class AttributeController {

    private AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    //для тестирования адрес: http://localhost:8080/attribute/all
    @GetMapping("/all")
    public List<Attribute> findAll(){

        return attributeService.findAll();
    }


    @PostMapping("/add")
    public ResponseEntity<Attribute> add(@RequestBody Attribute attribute){

        //проверка на обязательные параметры
        if(attribute.getAttributeId() != null && attribute.getAttributeId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attribute_name
        if(attribute.getAttributeName() == null || attribute.getAttributeName().trim().length() == 0){
            return new ResponseEntity("missed param: attribute_name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attributeService.add(attribute));//добавляем и сразу получаем объект и можем с ним работать
    }

    @PutMapping("update")
    public ResponseEntity<Attribute> update(@RequestBody Attribute attribute){

        //проверка на обязательные параметры
        if(attribute.getAttributeId() == null && attribute.getAttributeId() == 0){
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attribute_name
        if(attribute.getAttributeName() == null || attribute.getAttributeName().trim().length() == 0){
            return new ResponseEntity("missed param: attribute_name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attributeService.update(attribute));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Attribute> findById(@PathVariable Long id){

        Attribute attribute = null;

        try {
            attribute = attributeService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attribute);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            attributeService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

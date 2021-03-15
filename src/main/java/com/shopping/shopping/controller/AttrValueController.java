package com.shopping.shopping.controller;

import com.shopping.shopping.entity.AttrValue;
import com.shopping.shopping.service.AttrValueService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/attrvalue")
public class AttrValueController {

    private AttrValueService attrValueService;

    public AttrValueController(AttrValueService attrValueService) {
        this.attrValueService = attrValueService;
    }

    //для тестирования адрес: http://localhost:8080/attrvalue/all
    @GetMapping("/all")
    public List<AttrValue> findAll(){

        return attrValueService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<AttrValue> add(@RequestBody AttrValue attrvalue){

        //проверка на обязательные параметры
        if(attrvalue.getAttrValueId() != null && attrvalue.getAttrValueId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attrvalue
        if(attrvalue.getAttrValue() == null || attrvalue.getAttrValue().trim().length() == 0){
            return new ResponseEntity("missed param: attrvalue", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attrValueService.add(attrvalue));//добавляем и сразу получаем объект и можем с ним работать
    }

    @PutMapping("update")
    public ResponseEntity<AttrValue> update(@RequestBody AttrValue attrvalue){

        //проверка на обязательные параметры
        if(attrvalue.getAttrValueId() == null && attrvalue.getAttrValueId() == 0){
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attrvalue
        if(attrvalue.getAttrValue() == null || attrvalue.getAttrValue().trim().length() == 0){
            return new ResponseEntity("missed param: attrvalue", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attrValueService.update(attrvalue));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AttrValue> findById(@PathVariable Long id){

        AttrValue attrvalue = null;

        try {
            attrvalue = attrValueService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attrvalue);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            attrValueService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

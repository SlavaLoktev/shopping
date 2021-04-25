package com.shopping.shopping.controller;

import com.shopping.shopping.entity.AttrValue;
import com.shopping.shopping.repository.AttrValueRepository;
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

    private final AttrValueService attrValueService;

    public AttrValueController(AttrValueService attrValueService) {
        this.attrValueService = attrValueService;
    }

    @GetMapping("/all")
    public List<AttrValue> findAll(){

        return attrValueService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<AttrValue> add(@RequestBody AttrValue attrValue){

        if(attrValue.getAttrValueId() != null && attrValue.getAttrValueId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attrValue
        if(attrValue.getAttrValue() == null || attrValue.getAttrValue().trim().length() == 0){
            return new ResponseEntity("missed param: attrValue", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attrValueService.add(attrValue));
    }

    @PutMapping("update")
    public ResponseEntity<AttrValue> update(@RequestBody AttrValue attrValue) {

        //проверка на обязательные параметры
        if (attrValue.getAttrValueId() == null && attrValue.getAttrValueId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attrValue
        if (attrValue.getAttrValue() == null || attrValue.getAttrValue().trim().length() == 0) {
            return new ResponseEntity("missed param: attrValue", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attrValueService.update(attrValue));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AttrValue> findById(@PathVariable Long id){

        AttrValue attrValue = null;

        try {
            attrValue = attrValueService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attrValue);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            attrValueService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}

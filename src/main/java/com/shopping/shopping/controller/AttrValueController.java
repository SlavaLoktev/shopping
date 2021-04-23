package com.shopping.shopping.controller;

import com.shopping.shopping.entity.AttrValue;
import com.shopping.shopping.repository.AttrValueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok(attrValueRepository.save(attrValue));
    }

    @PutMapping("update")
    public ResponseEntity update(@RequestBody AttrValue attrValue) {

        //проверка на обязательные параметры
        if (attrValue.getAttrValueId() == null && attrValue.getAttrValueId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        //если передали пустое значение attrValue
        if (attrValue.getAttrValue() == null || attrValue.getAttrValue().trim().length() == 0) {
            return new ResponseEntity("missed param: attrValue", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(attrValueRepository.save(attrValue));
    }
}

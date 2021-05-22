package com.shopping.shopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CommonController<E> {

    boolean checkParams(@RequestBody E entity, String operationType);

    @GetMapping("/all")
    ResponseEntity<List<E>> findAll();

    @PostMapping("/add")
    ResponseEntity<E> add(@RequestBody E entity);

    @PutMapping("update")
    ResponseEntity<E> update(@RequestBody E entity);

    @GetMapping("/id/{id}")
    ResponseEntity<E> findById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    ResponseEntity delete(@PathVariable Long id);
}

package com.shopping.shopping.controller;

import com.shopping.shopping.service.CommonService;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractController<E, S extends CommonService<E>> implements CommonController<E>{

    private static final Logger LOGGER = Logger.getLogger(AbstractController.class);

    private final S service;

    public AbstractController(S service) {
        this.service = service;
    }

    @Override
    public boolean checkParams(@RequestBody E entity, String operationType) {
        return true;
    }

    @Override
    public ResponseEntity<List<E>> findAll(){

        List<E> result = null;

        try {
            result = service.getAll();
        }catch (EmptyResultDataAccessException e){
            LOGGER.error(String.format("%s, item(s) can not found", HttpStatus.NOT_FOUND));
        }

        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<E> add(@RequestBody E entity){

        if (!checkParams(entity, "add")){
            return new ResponseEntity("Incorrect params", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info(String.format("Added: %s", entity));

        return ResponseEntity.ok(service.save(entity));
    }

    @Override
    public ResponseEntity<E> update(@RequestBody E entity){

        if (!checkParams(entity, "update")){
            return new ResponseEntity("Incorrect params", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info(String.format("Updated: %s", entity));

        return ResponseEntity.ok(service.save(entity));
    }

    @Override
    public ResponseEntity<E> findById(@PathVariable Long id){

        E entity = null;

        try {
            entity = service.get(id);
        }catch (NoSuchElementException e){
            LOGGER.error(String.format("Id %d not found, error: %s", id, e));
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info(String.format("%s found", entity));

        return ResponseEntity.ok(entity);
    }

    @Override
    public ResponseEntity delete(@PathVariable Long id){

        try {
            service.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            LOGGER.error(String.format("Id %d not found, error: %s", id, e));
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info(String.format("%s, deleted", HttpStatus.OK));

        return new ResponseEntity(HttpStatus.OK);
    }
}

package com.shopping.shopping.service;

import com.shopping.shopping.repository.CommonRepository;

import java.util.List;

public abstract  class AbstractService<E, R extends CommonRepository<E>> implements CommonService<E>{

    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    public List<E> getAll(){
        return repository.findAll();
    }

    public E save(E object){
        return repository.save(object);
    }

    public E get(Long id){
        return repository.findById(id).get();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}

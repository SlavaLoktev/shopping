package com.shopping.shopping.service;

import com.shopping.shopping.entity.Attribute;
import com.shopping.shopping.repository.AttributeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AttributeService {

    private final AttributeRepository repository;

    public AttributeService(AttributeRepository repository) {
        this.repository = repository;
    }

    public List<Attribute> findAll(){
        return repository.findAll();
    }

    public Attribute add(Attribute attribute){
        return repository.save(attribute);//метод save обновляет или создает новый объект, если его не было
    }

    public Attribute update(Attribute attribute){
        return repository.save(attribute);//метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Attribute findById(Long id){
        return repository.findById(id).get();// т.к. возвращается optional - нужно  получить объект методом get()
    }
}
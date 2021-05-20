package com.shopping.shopping.service;

import com.shopping.shopping.entity.AttrValue;
import com.shopping.shopping.repository.AttrValueRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AttrValueService {

    private final AttrValueRepository repository;

    public AttrValueService(AttrValueRepository repository) {
        this.repository = repository;
    }

    public List<AttrValue> findAll(){
        return repository.findAll();
    }

    public AttrValue add(AttrValue attrValue){
        return repository.save(attrValue);
    }

    public AttrValue update(AttrValue attrValue){
        return repository.save(attrValue);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public AttrValue findById(Long id) {
        return repository.findById(id).get();
    }
}

package com.shopping.shopping.service;

import com.shopping.shopping.entity.Attribute;
import com.shopping.shopping.repository.AttributeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AttributeService extends AbstractService<Attribute, AttributeRepository>{

    public AttributeService(AttributeRepository repository) {
        super(repository);
    }
}

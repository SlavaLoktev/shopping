package com.shopping.shopping.service;

import com.shopping.shopping.entity.AttrValue;
import com.shopping.shopping.repository.AttrValueRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AttrValueService extends AbstractService<AttrValue, AttrValueRepository>{

    public AttrValueService(AttrValueRepository repository) {
        super(repository);
    }
}

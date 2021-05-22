package com.shopping.shopping.service;

import com.shopping.shopping.entity.Reviews;
import com.shopping.shopping.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewsService extends AbstractService<Reviews, ReviewsRepository>{

    private final ReviewsRepository repository;

    public ReviewsService(ReviewsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Reviews> findByParams(Long productId){
        return repository.findByParams(productId);
    }
}

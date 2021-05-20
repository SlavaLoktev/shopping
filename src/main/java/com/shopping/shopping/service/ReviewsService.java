package com.shopping.shopping.service;

import com.shopping.shopping.entity.Reviews;
import com.shopping.shopping.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewsService {

    private final ReviewsRepository repository;

    public ReviewsService(ReviewsRepository repository) {
        this.repository = repository;
    }

    public List<Reviews> findAll(){
        return repository.findAll();
    }

    public Reviews add(Reviews reviews){
        return repository.save(reviews);
    }

    public Reviews update(Reviews reviews){
        return repository.save(reviews);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Reviews findById(Long id){
        return repository.findById(id).get();
    }

    public List<Reviews> findByParams(Long product){
        return repository.findByParams(product);
    }
}

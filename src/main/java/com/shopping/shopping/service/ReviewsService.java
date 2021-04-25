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
        return repository.save(reviews);//метод save обновляет или создает новый объект, если его не было
    }

    public Reviews update(Reviews reviews){
        return repository.save(reviews);//метод save обновляет или создает новый объект, если его не было
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Reviews findById(Long id){
        return repository.findById(id).get();// т.к. возвращается optional - нужно  получить объект методом get()
    }
}

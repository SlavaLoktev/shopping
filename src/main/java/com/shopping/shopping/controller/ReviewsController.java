package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Reviews;
import com.shopping.shopping.repository.ReviewsRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private ReviewsRepository reviewsRepository;

    public ReviewsController(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @GetMapping("/test")
    public List<Reviews> test(){

        List<Reviews> list = reviewsRepository.findAll();

        return list;
    }

    @PostMapping("/add")
    public ResponseEntity<Reviews> add(@RequestBody Reviews reviews){

        if(reviews.getReviewId() != null && reviews.getReviewId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getRating() == null || reviews.getRating() == 0){
            return new ResponseEntity("missed param: rating", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getReviewDate() == null){
            return new ResponseEntity("missed param: reviewDate", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(reviewsRepository.save(reviews));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Reviews reviews){

        if(reviews.getReviewId() == null && reviews.getReviewId() == 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getRating() == null || reviews.getRating() == 0){
            return new ResponseEntity("missed param: rating", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getReviewDate() == null){
            return new ResponseEntity("missed param: reviewDate", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(reviewsRepository.save(reviews));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Reviews> findById(@PathVariable Long id){

        Reviews reviews = null;

        try {
            reviews = reviewsRepository.findById(id).get();
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            reviewsRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}

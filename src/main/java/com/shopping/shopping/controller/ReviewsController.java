package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Reviews;
import com.shopping.shopping.service.ReviewsService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    //для тестирования адрес: http://localhost:8080/reviews/all
    @GetMapping("/all")
    public List<Reviews> findAll(){

        return reviewsService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Reviews> add(@RequestBody Reviews reviews){

        //проверка на обязательные параметры
        if(reviews.getReviewId() != null && reviews.getReviewId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //проверка на обязательные параметры
        if(reviews.getCustomerId() != null && reviews.getCustomerId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //проверка на обязательные параметры
        if(reviews.getProductId() != null && reviews.getProductId() != 0){
            //id создается автоматически в БД, поэтому его не нужно передавать
            return new ResponseEntity("redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(reviewsService.add(reviews));//добавляем и сразу получаем объект и можем с ним работать
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Reviews> findById(@PathVariable Long id){

        Reviews reviews = null;

        try {
            reviews = reviewsService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            reviewsService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + "not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}

package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Reviews;
import com.shopping.shopping.repository.ReviewsRepository;
import com.shopping.shopping.search.ReviewsSearchValues;
import com.shopping.shopping.service.ReviewsService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewsController {

    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/all")
    public List<Reviews> findAll(){

        return reviewsService.findAll();
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

        return ResponseEntity.ok(reviewsService.add(reviews));
    }

    @PutMapping("/update")
    public ResponseEntity<Reviews> update(@RequestBody Reviews reviews){

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

        return ResponseEntity.ok(reviewsService.update(reviews));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Reviews> findById(@PathVariable Long id){

        Reviews reviews = null;

        try {
            reviews = reviewsService.findById(id);
        }catch (NoSuchElementException e){ //если объект не будет найден
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            reviewsService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Reviews>> search(@RequestBody ReviewsSearchValues reviewsSearchValues){

        Long product = reviewsSearchValues.getProduct() != null ? reviewsSearchValues.getProduct() : null;
        return ResponseEntity.ok(reviewsService.findByParams(product));
    }
}

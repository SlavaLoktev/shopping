package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Reviews;
import com.shopping.shopping.search.ReviewsSearchValues;
import com.shopping.shopping.service.ReviewsService;
import org.apache.log4j.Logger;
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

    private static Logger LOGGER = Logger.getLogger(ProductController.class);

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping("/all")
    public List<Reviews> findAll(){

        if (reviewsService.findAll().size() == 1){
            LOGGER.info(reviewsService.findAll().size() + " review found");
        }
        if (reviewsService.findAll().size() > 1){
            LOGGER.info(reviewsService.findAll().size() + " reviews found");
        }

        return reviewsService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Reviews> add(@RequestBody Reviews reviews){

        if(reviews.getReviewId() != null && reviews.getReviewId() != 0){
            LOGGER.error("Redundand param: id must be null");
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getRating() == null || reviews.getRating() == 0){
            LOGGER.error("Missed param: rating");
            return new ResponseEntity("Missed param: rating", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getReviewDate() == null){
            LOGGER.error("Missed param: reviewDate");
            return new ResponseEntity("Missed param: reviewDate", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Added review: " + reviews);

        return ResponseEntity.ok(reviewsService.add(reviews));
    }

    @PutMapping("/update")
    public ResponseEntity<Reviews> update(@RequestBody Reviews reviews){

        if(reviews.getReviewId() == null && reviews.getReviewId() == 0){
            LOGGER.error("Missed param: id");
            return new ResponseEntity("Missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getRating() == null || reviews.getRating() == 0){
            LOGGER.error("Missed param: rating");
            return new ResponseEntity("Missed param: rating", HttpStatus.NOT_ACCEPTABLE);
        }

        if(reviews.getReviewDate() == null){
            LOGGER.error("Missed param: reviewDate");
            return new ResponseEntity("Missed param: reviewDate", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Updated review: " + reviews);

        return ResponseEntity.ok(reviewsService.update(reviews));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Reviews> findById(@PathVariable Long id){

        Reviews reviews = null;

        try {
            reviews = reviewsService.findById(id);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Review " + reviews + " found");

        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        try {
            reviewsService.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            LOGGER.error("Id = " + id + " not found");
            return new ResponseEntity("Id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        LOGGER.info("Deleted review with id: " + id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Reviews>> search(@RequestBody ReviewsSearchValues reviewsSearchValues){

        Long product = reviewsSearchValues.getProduct() != null ? reviewsSearchValues.getProduct() : null;

        List<Reviews> result = reviewsService.findByParams(product);

        if (result.size() == 1){
            LOGGER.info(result.size() + " review found");
        }

        if (result.size() > 1){
            LOGGER.info(result.size() + " reviews found");
        }

        return ResponseEntity.ok(result);
    }
}

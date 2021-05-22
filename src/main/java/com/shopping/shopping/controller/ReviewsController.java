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

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewsController extends AbstractController<Reviews, ReviewsService>{

    private static final Logger LOGGER = Logger.getLogger(ReviewsController.class);

    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        super(reviewsService);
        this.reviewsService = reviewsService;
    }

    public boolean checkAdditionalParams(Reviews reviews){

        if(reviews.getRating() == null || reviews.getRating() == 0){
            LOGGER.error("Missed param: rating");
            return false;
        }

        if(reviews.getReviewDate() == null){
            LOGGER.error("Missed param: reviewDate");
            return false;
        }

        return true;
    }

    @Override
    public boolean checkParams(@RequestBody Reviews reviews, String operationType){

        switch (operationType){
            case "add":
                if(reviews.getReviewId() != null){
                    LOGGER.error("Redundand param: id must be null");
                    return false;
                }
                if(!checkAdditionalParams(reviews)){
                    return false;
                }
                break;
            case "update":
                if(reviews.getReviewId() == null || reviews.getReviewId() == 0){
                    LOGGER.error("Missed param: id");
                    return false;
                }
                if(!checkAdditionalParams(reviews)){
                    return false;
                }
                break;
            default:
                return true;
        }
        return true;
    }

    @PostMapping("/search")
    public ResponseEntity<List<Reviews>> search(@RequestBody ReviewsSearchValues reviewsSearchValues){

        Long product = reviewsSearchValues.getProduct() != null ? reviewsSearchValues.getProduct() : null;

        List<Reviews> result = null;

        try {
            result = reviewsService.findByParams(product);
        }catch (EmptyResultDataAccessException e){
            LOGGER.error(String.format("%s, review(s) can not found", HttpStatus.NOT_FOUND));
        }

        LOGGER.error(String.format("%d review(s) found", result.size()));

        return ResponseEntity.ok(result);
    }
}

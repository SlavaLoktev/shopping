package com.shopping.shopping.controller;

import com.shopping.shopping.entity.Reviews;
import com.shopping.shopping.repository.ReviewsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}

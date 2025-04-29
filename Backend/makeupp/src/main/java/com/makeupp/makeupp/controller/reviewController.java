package com.makeupp.makeupp.controller;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.reviewDTO;
import com.makeupp.makeupp.service.reviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class reviewController {

    @Autowired
    private reviewService reviewService;

    @PostMapping("/")
    public ResponseEntity<Object> registerReview(@RequestBody reviewDTO review) {
        responseDTO respuesta = reviewService.save(review);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllReviews() {
        var lista = reviewService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneReview(@PathVariable int id) {
        var opt = reviewService.findById(id);
        if (opt.isEmpty()) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(opt.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable int id) {
        responseDTO respuesta = reviewService.deleteReview(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<reviewDTO>> getReviewsByProduct(@PathVariable int productId) {
        List<reviewDTO> reviews = reviewService.findByProductId(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}

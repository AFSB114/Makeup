
package com.makeupp.makeupp.controller;

import org.springframework.web.bind.annotation.RestController;
import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.reviewDTO;
import com.makeupp.makeupp.service.reviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        var listaReviews = reviewService.findAll();
        return new ResponseEntity<>(listaReviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneReview(@PathVariable int id) {
        var review = reviewService.findById(id);
        if (!review.isPresent()) 
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable int id) {
        var respuesta = reviewService.deleteReview(id);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
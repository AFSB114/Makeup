package com.makeupp.makeupp.service;

import com.makeupp.makeupp.DTO.responseDTO;
import com.makeupp.makeupp.DTO.reviewDTO;
import com.makeupp.makeupp.model.review;
import com.makeupp.makeupp.model.user;
import com.makeupp.makeupp.model.product;
import com.makeupp.makeupp.repository.Ireview;
import com.makeupp.makeupp.repository.Iuser;
import com.makeupp.makeupp.repository.Iproduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class reviewService {
    
    @Autowired
    private Ireview data;
    
    @Autowired
    private Iuser userRepository;
    
    @Autowired
    private Iproduct productRepository;

    public List<review> findAll() {
        return data.findAll();
    }

    public Optional<review> findById(int id) {
        return data.findById(id);
    }

    public responseDTO deleteReview(int id) {
        if (!findById(id).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El registro no existe"
            );
            return respuesta;
        }
        data.deleteById(id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se eliminó correctamente"
        );
        return respuesta;
    }

    public responseDTO save(reviewDTO reviewDTO) {
        user user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        product product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        review reviewRegister = convertToModel(reviewDTO, user, product);
        data.save(reviewRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente"
        );
        return respuesta;
    }

    public reviewDTO convertToDTO(review review) {
        return new reviewDTO(
                review.getReviewId(),
                review.getUser().getId(),
                review.getProduct().getProduct_id(),
                review.getRating(),
                review.getComment(),
                review.getReviewDate()
        );
    }

    public review convertToModel(reviewDTO reviewDTO, user user, product product) {
        return new review(
                user,
                product,
                reviewDTO.getRating(),
                reviewDTO.getComment(),
                reviewDTO.getReviewDate()
        );
    }
}

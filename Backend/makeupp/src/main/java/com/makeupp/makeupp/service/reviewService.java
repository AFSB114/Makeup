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
import java.util.stream.Collectors;

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
            return new responseDTO(HttpStatus.OK.toString(), "El registro no existe");
        }
        data.deleteById(id);
        return new responseDTO(HttpStatus.OK.toString(), "Se eliminó correctamente");
    }

    public responseDTO save(reviewDTO reviewDTO) {
        user user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        product product = productRepository.findById(reviewDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        review reviewRegister = convertToModel(reviewDTO, user, product);
        data.save(reviewRegister);
        return new responseDTO(HttpStatus.OK.toString(), "Se guardó correctamente");
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

    public review convertToModel(reviewDTO dto, user user, product product) {
        return new review(
                user,
                product,
                dto.getRating(),
                dto.getComment(),
                dto.getReviewDate()
        );
    }

    public List<reviewDTO> findByProductId(int productId) {
        return data.findByProductId(productId)
                   .stream()
                   .map(this::convertToDTO)
                   .collect(Collectors.toList());
    }
    
}

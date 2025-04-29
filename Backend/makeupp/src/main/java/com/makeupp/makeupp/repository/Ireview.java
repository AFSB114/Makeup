package com.makeupp.makeupp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.makeupp.makeupp.model.review;

public interface Ireview extends JpaRepository<review, Integer> {

    @Query("SELECT r FROM review r WHERE r.product.product_id = :productId")
    List<review> findByProductId(@Param("productId") int productId);

}

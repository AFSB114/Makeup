package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.makeupp.makeupp.model.product;

public interface Iproduct extends JpaRepository<product, Integer> {
    Optional<product> findByName(String name);
}

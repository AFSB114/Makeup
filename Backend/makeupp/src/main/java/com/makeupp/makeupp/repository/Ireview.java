package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeupp.makeupp.model.review;

public interface Ireview extends JpaRepository<review, Integer> {
    /*
     * Métodos CRUD automáticos de JpaRepository
     */
}
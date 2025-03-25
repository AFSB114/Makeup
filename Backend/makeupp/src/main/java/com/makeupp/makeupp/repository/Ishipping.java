package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeupp.makeupp.model.shipping;

public interface Ishipping extends JpaRepository<shipping, Integer> {
    /*
     * Métodos CRUD automáticos de JpaRepository
     */
}
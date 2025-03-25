package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeupp.makeupp.model.cart;

public interface Icart extends JpaRepository<cart, Integer> {
    /*
     * Métodos CRUD automáticos de JpaRepository
     */
}
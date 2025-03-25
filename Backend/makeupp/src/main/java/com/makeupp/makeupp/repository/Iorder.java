package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeupp.makeupp.model.orders;


public interface Iorder extends JpaRepository<orders, Integer> {
    /*
     * Métodos CRUD automáticos de JpaRepository
     */
}
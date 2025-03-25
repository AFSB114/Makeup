package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeupp.makeupp.model.payment;

public interface Ipayment extends JpaRepository<payment, Integer> {
    /*
     * Métodos CRUD automáticos de JpaRepository
     */
}
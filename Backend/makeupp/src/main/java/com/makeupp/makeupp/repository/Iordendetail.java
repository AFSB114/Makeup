package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeupp.makeupp.model.order_detail;

public interface Iordendetail extends JpaRepository<order_detail, Integer> {
    /*
     * Métodos CRUD automáticos de JpaRepository
     */
}
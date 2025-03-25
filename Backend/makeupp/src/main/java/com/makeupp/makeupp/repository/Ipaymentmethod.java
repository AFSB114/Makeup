package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.makeupp.makeupp.model.payment_method;

public interface Ipaymentmethod extends JpaRepository<payment_method, Integer> {
}

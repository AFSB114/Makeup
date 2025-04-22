package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


import com.makeupp.makeupp.model.product;

public interface Iproduct extends JpaRepository<product, Integer> {

    @Query("SELECT p FROM product p WHERE p.name LIKE %?1%")
    List<product> getListProductForName(String filter);

    @Query("SELECT p FROM product p WHERE p.price < :filter")
    List<product> getListProductForPrice(int filter);

}

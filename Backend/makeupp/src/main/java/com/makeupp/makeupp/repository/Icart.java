package com.makeupp.makeupp.repository;

import com.makeupp.makeupp.model.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface Icart extends JpaRepository<cart, Integer> {
    List<cart> findByUser_Id(int userId); 
}
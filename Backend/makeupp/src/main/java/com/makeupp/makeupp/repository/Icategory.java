package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.makeupp.makeupp.model.category;

public interface Icategory extends JpaRepository<category, Integer> {
    // Métodos CRUD automáticos de JpaRepository
}

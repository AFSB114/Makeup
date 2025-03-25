package com.makeupp.makeupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.makeupp.makeupp.model.user;

public interface Iuser extends JpaRepository<user, Integer> {
    Optional<user> findByName(String name);
}

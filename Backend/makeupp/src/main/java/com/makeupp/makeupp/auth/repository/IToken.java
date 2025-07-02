package com.makeupp.makeupp.auth.repository;

import com.makeupp.makeupp.auth.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IToken extends JpaRepository<Token, Long> {
    @Query("SELECT t FROM Token t WHERE t.expired = FALSE AND t.locked = FALSE AND t.user.id = :id")
    List<Token> findAllValidTokensByUser(int id);
}

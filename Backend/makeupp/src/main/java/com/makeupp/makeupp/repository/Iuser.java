package com.makeupp.makeupp.repository;

import com.makeupp.makeupp.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface Iuser extends JpaRepository<user, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE user SET status = FALSE WHERE id = :id")
    void eliminar(int id);

    Optional<user> findByEmailAndPassword(String email, String password);

    @Query("SELECT c FROM user c WHERE c.status != false")
    List<user> getListUserActive();

    @Query("SELECT c FROM user c WHERE c.name LIKE %?1%")
    List<user> getListUserForName(String filter);
}

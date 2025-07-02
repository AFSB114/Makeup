package com.makeupp.makeupp.auth.model;

import com.makeupp.makeupp.model.user;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    private boolean expired;

    private boolean locked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;
}
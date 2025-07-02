package com.makeupp.makeupp.auth.controller;

import com.makeupp.makeupp.auth.DTO.ReqLogin;
import com.makeupp.makeupp.auth.DTO.ReqRegister;
import com.makeupp.makeupp.auth.DTO.TokenResponse;
import com.makeupp.makeupp.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class TokenController {

    private final AuthService authService;

    public TokenController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> registerUser(@RequestBody ReqRegister user) {
        System.out.print("Security Filter Chain <UNK>");
        var res = authService.register(user);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody ReqLogin user) {
        TokenResponse res = authService.login(user);
        return ResponseEntity.ok(res);
    }
}

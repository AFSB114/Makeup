package com.makeupp.makeupp.auth.service;

import com.makeupp.makeupp.auth.DTO.ReqLogin;
import com.makeupp.makeupp.auth.DTO.ReqRegister;
import com.makeupp.makeupp.auth.DTO.TokenResponse;
import com.makeupp.makeupp.auth.model.Token;
import com.makeupp.makeupp.auth.repository.IToken;
import com.makeupp.makeupp.model.user;
import com.makeupp.makeupp.repository.Iuser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Iuser userRepository;
    private final IToken tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public TokenResponse register(ReqRegister userRegister){
        user user = convertToModel(userRegister);
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, token);
        return new TokenResponse(token, refreshToken);
    }

    public TokenResponse login(ReqLogin userLogin){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );
        Optional<user> optionalUser = userRepository.findByEmail(userLogin.getEmail());
        if(optionalUser.isEmpty()) throw new UsernameNotFoundException("Invalid email or password");
        user user = optionalUser.get();
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserToken(user);
        saveUserToken(user, token);
        return new TokenResponse(token, refreshToken);
    }

    public void revokeAllUserToken(user user){
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) throw new UsernameNotFoundException("Invalid email or password");
        for (Token token : validUserTokens) {
            token.setExpired(true);
            token.setLocked(true);
        }
        tokenRepository.saveAll(validUserTokens);
    }

    public TokenResponse refreshToken(String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer ")) throw new IllegalArgumentException("Invalid bearer header");

        String refreshToken = authHeader.substring(7);
        String userEmail = jwtService.extractUserEmail(refreshToken);

        if (userEmail == null) throw new UsernameNotFoundException("Invalid email refresh token");

        Optional<user> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("Invalid email refresh token");
        user user = optionalUser.get();

        if (!jwtService.isTokenValid(refreshToken, user)) throw new IllegalArgumentException("Invalid refresh token");

        String accessToken = jwtService.generateToken(user);
        revokeAllUserToken(user);
        saveUserToken(user, accessToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    private void saveUserToken(user user, String jwtToken){
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .locked(false)
                .build();

        tokenRepository.save(token);
    }

    public user convertToModel(ReqRegister userRegister){
        return new user(
                userRegister.getName(),
                userRegister.getEmail(),
                passwordEncoder.encode(userRegister.getPassword()),
                userRegister.getAddress(),
                userRegister.getPhone(),
                userRegister.getRole()
        );
    }
}

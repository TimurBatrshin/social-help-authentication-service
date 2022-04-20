package com.example.socialhelp.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.socialhelp.dto.TokenDto;
import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.TokenRepository;
import com.example.socialhelp.repositories.UserRepository;
import com.example.socialhelp.security.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenDto generateToken(User user) {
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim(JwtTokenUtils.ROLE, user.getRole().toString())
                .withClaim(JwtTokenUtils.STATE, user.getState().toString())
                .withClaim(JwtTokenUtils.EMAIL, user.getEmail())
                .withClaim(JwtTokenUtils.ID, user.getId())
                .sign(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY));

        TokenDto tokenDto = TokenDto.builder()
                .token(token)
                .build();

        return tokenDto;


    }

    @Override
    public Map<String, Claim> verifyToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY))
                    .build().verify(token);
            return decodedJWT.getExpiresAt()
                    .after(new Date()) ? decodedJWT.getClaims() : null;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    @Override
    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY))
                .build().verify(token);
        return !decodedJWT.getExpiresAt()
                .after(new Date());
    }

    @Override
    public void saveToken(TokenDto token, User user) {
        try {
            if (tokenRepository.findTokenByUsers(user).getToken() == null){
                List<User> users = new ArrayList<>();
                users.add(user);
                Token refreshToken = Token.builder()
                        .token(token.getToken())
                        .build();
                refreshToken.setUsers(users);
                tokenRepository.save(refreshToken);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}

package com.example.socialhelp.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.socialhelp.dto.TokenDto;
import com.example.socialhelp.dto.TokenPairDto;
import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.TokenRepository;
import com.example.socialhelp.repositories.UserRepository;
import com.example.socialhelp.security.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository usersRepository;

    @Override
    public String generateAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim(JwtTokenUtils.ROLE, user.getRole().toString())
                .withClaim(JwtTokenUtils.EMAIL, user.getEmail())
                .withClaim(JwtTokenUtils.ID, user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(
                        new Date(System.currentTimeMillis() + JwtTokenUtils.ACCESS_TOKEN_LIFE_TIME))
                .sign(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY));
    }

    @Override
    public String generateRefreshToken(User user) {
        String refreshToken = UUID.randomUUID().toString();

        return refreshToken;
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
    public TokenPairDto refreshToken(TokenPairDto tokenPair) {
        Token token = tokenRepository.findTokenByRefreshToken(tokenPair.getRefreshToken()).orElseThrow(IllegalAccessError::new);

        Map<String, Claim> claims = getClaims(tokenPair.getAccessToken());

        User user = usersRepository.findById(claims.get(JwtTokenUtils.ID).asLong()).orElseThrow(IllegalAccessError::new);
        String newAccessToken = generateAccessToken(user);
        String newRefreshToken = UUID.randomUUID().toString();

        token.setRefreshToken(newRefreshToken);
        token.setCreate_time(new Date());
        tokenRepository.save(token);

        return TokenPairDto.builder()
                .refreshToken(newRefreshToken)
                .accessToken(newAccessToken)
                .build();
    }

    @Override
    public Map<String, Claim> getClaims(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(JwtTokenUtils.SECRET_KEY))
                    .build().verify(token).getClaims();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    @Override
    public TokenPairDto generateTokenPair(User user) {
        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);
        Token token = Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .create_time(new Date())
                .user(user)
                .build();
        tokenRepository.save(token);

        return new TokenPairDto(accessToken, refreshToken);
    }

}
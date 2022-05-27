package com.example.socialhelp.services;

import com.auth0.jwt.interfaces.Claim;
import com.example.socialhelp.dto.TokenDto;
import com.example.socialhelp.dto.TokenPairDto;
import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;


import java.util.Map;

public interface TokenService {

    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    Map<String, Claim> verifyToken(String token);

    boolean validateToken(String token);

    TokenPairDto refreshToken(TokenPairDto tokenPair);

    Map<String, Claim> getClaims(String token);

    TokenPairDto generateTokenPair(User user);

}

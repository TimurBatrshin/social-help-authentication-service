package com.example.socialhelp.services;

import com.auth0.jwt.interfaces.Claim;
import com.example.socialhelp.dto.TokenDto;
import com.example.socialhelp.models.User;


import java.util.Map;

public interface TokenService {

    TokenDto generateToken(User user);
    Map<String, Claim> verifyToken(String token);
    boolean validateToken(String token);
    void saveToken(TokenDto token, User user);
}

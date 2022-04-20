package com.example.socialhelp.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.socialhelp.dto.LoginDto;
import com.example.socialhelp.dto.SignUpForm;
import com.example.socialhelp.dto.TokenDto;
import com.example.socialhelp.models.User;
import com.example.socialhelp.security.util.JwtTokenUtils;
import com.example.socialhelp.services.LoginService;
import com.example.socialhelp.services.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
@CrossOrigin
public class SignInController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Tag(name = "Аутентификация")
    @PermitAll
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            User user = loginService.login(loginDto);
            TokenDto tokenDto = tokenService.generateToken(user);
            tokenService.saveToken(tokenDto, user);
            return ResponseEntity.ok(tokenDto);
        } catch (UsernameNotFoundException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", e.getMessage());
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }
}

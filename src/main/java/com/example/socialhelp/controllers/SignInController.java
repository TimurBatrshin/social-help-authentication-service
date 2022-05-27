package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.LoginDto;
import com.example.socialhelp.dto.TokenPairDto;
import com.example.socialhelp.models.User;
import com.example.socialhelp.services.LoginService;
import com.example.socialhelp.services.TokenService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class SignInController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @PermitAll
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        try {
            System.out.println(loginDto);
            User user = loginService.login(loginDto);
            TokenPairDto tokenPairDto = tokenService.generateTokenPair(user);
            return ResponseEntity.ok(tokenPairDto);
        } catch (UsernameNotFoundException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status", e.getMessage());
            return new ResponseEntity<>(jsonObject, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("login")
    public ResponseEntity<?> getTokens(){
        return null;
    }
}

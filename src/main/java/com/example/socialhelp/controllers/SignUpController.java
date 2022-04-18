package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.SignUpForm;
import com.example.socialhelp.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PermitAll
    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp(@RequestBody SignUpForm signUpForm) {
        System.out.println(signUpForm);
        return ResponseEntity.ok(signUpService.signUp(signUpForm));
    }
}

package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.EditProfileDto;
import com.example.socialhelp.dto.UserDto;
import com.example.socialhelp.models.User;
import com.example.socialhelp.security.authentication.JwtAuthentication;
import com.example.socialhelp.security.details.UserDetailsImpl;
import com.example.socialhelp.services.TokenService;
import com.example.socialhelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@RestController
@CrossOrigin
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getProfile(HttpServletRequest httpServletRequest) {
        UserDto user = userService.findUserByAccessToken(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/editProfile")
    @PermitAll
    public ResponseEntity<?> editProfile(@RequestBody EditProfileDto editProfileDto){
        userService.editProfile(1L, editProfileDto);
        return ResponseEntity.ok(editProfileDto);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));

    }

}

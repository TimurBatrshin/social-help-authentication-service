package com.example.socialhelp.controllers;

import com.example.socialhelp.models.User;
import com.example.socialhelp.security.details.UserDetailsImpl;
import com.example.socialhelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userService.findUserById(userDetails.getUser().getId());
        return ResponseEntity.ok(user);
    }

}

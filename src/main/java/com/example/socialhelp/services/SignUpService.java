package com.example.socialhelp.services;

import com.example.socialhelp.dto.SignUpForm;
import com.example.socialhelp.models.User;

public interface SignUpService {
    User signUp(SignUpForm signUpForm);
}

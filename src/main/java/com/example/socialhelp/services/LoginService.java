package com.example.socialhelp.services;

import com.example.socialhelp.dto.LoginDto;
import com.example.socialhelp.models.User;


public interface LoginService {

    User login(LoginDto loginDto);

}

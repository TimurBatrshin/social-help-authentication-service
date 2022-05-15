package com.example.socialhelp.services;

import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;

public interface UserService {
    User findUserById(Long id);
}

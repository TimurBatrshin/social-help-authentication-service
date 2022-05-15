package com.example.socialhelp.services;

import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}

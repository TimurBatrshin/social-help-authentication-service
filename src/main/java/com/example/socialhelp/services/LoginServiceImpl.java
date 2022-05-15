package com.example.socialhelp.services;

import com.example.socialhelp.dto.LoginDto;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(LoginDto loginDto) {
        User loginUser = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("user with this email not found"));


        return passwordEncoder
                .matches(loginDto.getHashPassword(), loginUser.getHashPassword()) ? loginUser : null;
    }
}

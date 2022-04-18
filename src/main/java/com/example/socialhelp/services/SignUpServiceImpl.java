package com.example.socialhelp.services;

import com.example.socialhelp.dto.SignUpForm;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.UserRepository;
import com.example.socialhelp.util.EmailUtil;
import com.example.socialhelp.util.MailsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public User signUp(SignUpForm signUpForm) {
        User newUser = User.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .email(signUpForm.getEmail().trim())
                .hashPassword(passwordEncoder.encode(signUpForm.getPassword()))
                .confirmCode(UUID.randomUUID().toString())
                .role(User.Role.ADMIN)
                .state(User.State.ACTIVE)
                .confirm(User.Confirm.NOT_CONFIRM)
                .age(signUpForm.getAge())
                .gender(signUpForm.getGender())
                .rating(0)
                .build();
        userRepository.save(newUser);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());

        emailUtil.sendMail(newUser.getEmail(), "Регистрация", from, confirmMail);

        return newUser;
    }
}

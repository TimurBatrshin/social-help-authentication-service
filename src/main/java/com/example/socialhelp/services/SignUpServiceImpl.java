package com.example.socialhelp.services;

import com.example.socialhelp.dto.SignUpForm;
import com.example.socialhelp.models.User;
import com.example.socialhelp.models.Сategory;
import com.example.socialhelp.repositories.SpecializationsRepository;
import com.example.socialhelp.repositories.UserRepository;
import com.example.socialhelp.repositories.СategoryRepository;
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
    private СategoryRepository categoryRepository;

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
        Сategory category = categoryRepository.getFirstByCategory(signUpForm.getCategory());

        User newUser = User.builder()
                .firstName(signUpForm.getFirstName().trim())
                .lastName(signUpForm.getLastName().trim())
                .email(signUpForm.getEmail().trim())
                .hashPassword(passwordEncoder.encode(signUpForm.getPassword().trim()))
                .confirmCode(UUID.randomUUID().toString())
                .role(User.Role.ADMIN)
                .state(User.State.ACTIVE)
                .confirm(User.Confirm.NOT_CONFIRM)
                .age(signUpForm.getAge().trim())
                .gender(signUpForm.getGender().trim())
                .categories(category)
                .rating(0)
                .build();
        userRepository.save(newUser);

        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());

        emailUtil.sendMail(newUser.getEmail(), "Регистрация", from, confirmMail);

        return newUser;
    }

}

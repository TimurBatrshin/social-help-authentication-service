package com.example.socialhelp.services;

import com.example.socialhelp.dto.SignUpForm;
import com.example.socialhelp.models.AdditionalInfoForSpecialist;
import com.example.socialhelp.models.Category;
import com.example.socialhelp.models.Specialization;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.AdditionalInfoForSpecialistRepository;
import com.example.socialhelp.repositories.CategoryRepository;
import com.example.socialhelp.repositories.SpecializationsRepository;
import com.example.socialhelp.repositories.UserRepository;
import com.example.socialhelp.util.EmailUtil;
import com.example.socialhelp.util.MailsGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdditionalInfoForSpecialistRepository additionalInfoForSpecialistRepository;

    @Autowired
    private SpecializationsRepository specializationsRepository;

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
                .firstName(signUpForm.getFirstName().trim())
                .lastName(signUpForm.getLastName().trim())
                .patronymic(signUpForm.getPatronymic())
                .email(signUpForm.getEmail().trim())
                .hashPassword(passwordEncoder.encode(signUpForm.getHashPassword().trim()))
                .birthday(signUpForm.getBirthday())
                .city(signUpForm.getCity())
                .role(User.Role.ADMIN)
                .state(User.State.ACTIVE)
                .gender(signUpForm.getGender().trim())
                .rate(0)
                .avatarUrl(signUpForm.getAvatarUrl())
                .bloodType(User.BloodType.M1)
                .isSpecialist(signUpForm.isSpecialist())
                .build();


        if (newUser.isSpecialist()) {
            AdditionalInfoForSpecialist additionalInfoForSpecialist = AdditionalInfoForSpecialist.builder()
                    .user(newUser)
                    .specialization(specializationsRepository.findSpecializationBySpecializationType(signUpForm.getSpecialization()))
                    .isConfirmedOnPlatform(false)
                    .qualification(signUpForm.getQualification())
                    .education(signUpForm.getEducation())
                    .qualification_improvement(signUpForm.getQualification_improvement())
                    .experience(signUpForm.getExperience())

                    .specialization(specializationsRepository.findSpecializationBySpecializationType(signUpForm.getSpecialization()))
                    .build();
            userRepository.save(newUser);
            additionalInfoForSpecialistRepository.save(additionalInfoForSpecialist);
        }else userRepository.save(newUser);



//        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, newUser.getConfirmCode());
//
//        emailUtil.sendMail(newUser.getEmail(), "Регистрация", from, confirmMail);

        return newUser;
    }

}

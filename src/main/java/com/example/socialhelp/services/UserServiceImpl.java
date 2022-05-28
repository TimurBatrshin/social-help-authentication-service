package com.example.socialhelp.services;

import com.example.socialhelp.dto.EditProfileDto;
import com.example.socialhelp.dto.ProblemUserDto;
import com.example.socialhelp.dto.UserDto;
import com.example.socialhelp.models.AdditionalInfoForSpecialist;
import com.example.socialhelp.models.Specialization;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.AdditionalInfoForSpecialistRepository;
import com.example.socialhelp.repositories.SpecializationsRepository;
import com.example.socialhelp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecializationsRepository specializationsRepository;

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return UserDto.mapUser(user);
    }

    @Override
    public UserDto findUserByAccessToken(String accessToken) {
        Optional<User> user = userRepository.findUserByAccessToken(accessToken);
        System.out.println(user);
        if (user.isPresent()) {
            return UserDto.mapUser(user.get());
        }
        throw new IllegalArgumentException("Пользователь на найден");
    }

    @Override
    public List<ProblemUserDto> findSpecialistByProblemId(Long id) {
        List<User> user = userRepository.findByProblemId(id);
        List<ProblemUserDto> problemUserDtos = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            ProblemUserDto problemUserDto = ProblemUserDto.builder()
                    .specialization(user.get(i).getAdditionalInfoForSpecialist().getSpecialization().getSpecializationType())
                    .avatarUrl(user.get(i).getAvatarUrl())
                    .city(user.get(i).getCity())
                    .firstName(user.get(i).getFirstName())
                    .lastName(user.get(i).getLastName())
                    .rate(user.get(i).getRate())
                    .build();
            problemUserDtos.add(problemUserDto);
        }
        return problemUserDtos;
    }

    @Override
    public List<ProblemUserDto> findUserBySpecializationId(Long id) {
        List<User> user = userRepository.findUserBySpecializationId(id);
        List<ProblemUserDto> problemUserDtos = new ArrayList<>();
        for (int i = 0; i < user.size(); i++) {
            ProblemUserDto problemUserDto = ProblemUserDto.builder()
                    .id(user.get(i).getId())
                    .specialization(user.get(i).getAdditionalInfoForSpecialist().getSpecialization().getSpecializationType())
                    .avatarUrl(user.get(i).getAvatarUrl())
                    .city(user.get(i).getCity())
                    .firstName(user.get(i).getFirstName())
                    .lastName(user.get(i).getLastName())
                    .rate(user.get(i).getRate())
                    .experience(user.get(i).getAdditionalInfoForSpecialist().getExperience())
                    .build();
            problemUserDtos.add(problemUserDto);
        }
        return problemUserDtos;
    }

    @Override
    public void editProfile(Long id, EditProfileDto editProfileDto) {
        User user = userRepository.findUserById(id);
        user.setFirstName(editProfileDto.getFirstName());
        user.setLastName(editProfileDto.getLastName());
        user.setCity(editProfileDto.getCity());
        user.setGender(editProfileDto.getGender());
        userRepository.save(user);
    }
}

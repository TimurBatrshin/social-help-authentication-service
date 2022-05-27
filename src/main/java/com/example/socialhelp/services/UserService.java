package com.example.socialhelp.services;

import com.example.socialhelp.dto.EditProfileDto;
import com.example.socialhelp.dto.ProblemUserDto;
import com.example.socialhelp.dto.UserDto;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {
    UserDto findUserById(Long id);
    UserDto findUserByAccessToken(String accessToken);
    List<ProblemUserDto> findSpecialistByProblemId(Long id);
    List<ProblemUserDto> findUserBySpecializationId(Long id);
    void editProfile(Long id, EditProfileDto editProfileDto);
}

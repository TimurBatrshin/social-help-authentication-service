package com.example.socialhelp.dto;

import com.example.socialhelp.models.AdditionalInfoForSpecialist;
import com.example.socialhelp.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthday;
    private String city;
    private String email;
    private String gender;
    private String hashPassword;
    private boolean isEmailConfirmed;
    private double rate;
    private String avatarUrl;
    private boolean isSpecialist;
    private User.BloodType bloodType;
    private User.Role role;
    private User.State state;
    private AdditionalInfoForSpecialist additionalInfoForSpecialist;
    private String accessToken;

    public static UserDto mapUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .patronymic(user.getPatronymic())
                .birthday(user.getBirthday())
                .city(user.getCity())
                .email(user.getEmail())
                .gender(user.getGender())
                .isEmailConfirmed(user.isEmailConfirmed())
                .rate(user.getRate())
                .avatarUrl(user.getAvatarUrl())
                .isSpecialist(user.isSpecialist())
                .bloodType(user.getBloodType())
                .role(user.getRole())
                .state(user.getState())
                .additionalInfoForSpecialist(user.getAdditionalInfoForSpecialist())
                .build();
    }
}

package com.example.socialhelp.dto;

import com.example.socialhelp.validation.ValidNames;
import com.example.socialhelp.validation.ValidPassword;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidNames(
        message = "{errors.invalid.names}",
        name = "first_name",
        surname = "last_name"
)
@Schema(description = "Сущность пользователя")
public class SignUpForm {
//    private String firstName;
//    private String lastName;
//    @Email(message = "{errors.incorrect.email}")
//    private String email;
//    @ValidPassword(message = "{errors.invalid.password}")
//    private String password;
//    private String age;
//    private String confirmCode;
//    private String gender;
//    private String specializations;
//    private String category;

    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthday;
    private String city;
    @Email(message = "{errors.incorrect.email}")
    private String email;
    private String gender;
    @ValidPassword(message = "{errors.invalid.password}")
    private String hashPassword;
    private String avatarUrl;
    private boolean bannedState;
    private boolean isSpecialist;

    //isSpecialist
    private boolean isConfirmedOnPlatform;
    private String qualification;
    private String education;
    private String qualification_improvement;
    private int experience;
    private String specialization;



}

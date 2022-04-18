package com.example.socialhelp.dto;

import com.example.socialhelp.validation.ValidNames;
import com.example.socialhelp.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidNames(
        message = "{errors.invalid.names}",
        name = "first_name",
        surname = "last_name"
)
public class SignUpForm {
    private String firstName;
    private String lastName;
    @Email(message = "{errors.incorrect.email}")
    private String email;
    @ValidPassword(message = "{errors.invalid.password}")
    private String password;
    private String age;
    private String confirmCode;
    private String gender;



}

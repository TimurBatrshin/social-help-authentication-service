package com.example.socialhelp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditProfileDto {
    private String firstName;
    private String lastName;
    private String city;
    private String gender;
}

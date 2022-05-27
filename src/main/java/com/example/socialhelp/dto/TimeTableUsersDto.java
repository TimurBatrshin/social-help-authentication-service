package com.example.socialhelp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeTableUsersDto {
    private Long timeStamp;
    private String firstNameUser;
    private String lastNameUser;
    private String description;
    private String firstNameSpec;
    private String lastNameSpec;
}

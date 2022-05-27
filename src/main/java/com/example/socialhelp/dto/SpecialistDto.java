package com.example.socialhelp.dto;

import com.example.socialhelp.models.Specialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialistDto {
    private String firstName;
    private String lastName;
    private String specialization;
    private String avatarUrl;
}

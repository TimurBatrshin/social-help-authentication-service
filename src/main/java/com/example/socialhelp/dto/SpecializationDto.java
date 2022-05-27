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
public class SpecializationDto {
    private Long id;
    private String specialization;

    public static SpecializationDto mapSpecialization(Specialization specialization){
        return SpecializationDto.builder()
                .id(specialization.getId())
                .specialization(specialization.getSpecializationType())
                .build();
    }
}

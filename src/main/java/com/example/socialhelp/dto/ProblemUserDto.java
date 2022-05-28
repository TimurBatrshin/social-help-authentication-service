package com.example.socialhelp.dto;

import com.example.socialhelp.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private double rate;
    private String avatarUrl;
    private String specialization;
    private int experience;
}

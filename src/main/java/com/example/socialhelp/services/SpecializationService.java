package com.example.socialhelp.services;

import com.example.socialhelp.dto.SpecializationDto;
import com.example.socialhelp.models.Specialization;
import com.example.socialhelp.models.User;

import java.util.List;

public interface SpecializationService {
    List<SpecializationDto> findAll();

}

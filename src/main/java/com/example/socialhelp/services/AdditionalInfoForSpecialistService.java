package com.example.socialhelp.services;

import com.example.socialhelp.dto.SpecialistDto;
import com.example.socialhelp.models.AdditionalInfoForSpecialist;

import java.util.List;

public interface AdditionalInfoForSpecialistService {
    List<SpecialistDto> findAll();
}

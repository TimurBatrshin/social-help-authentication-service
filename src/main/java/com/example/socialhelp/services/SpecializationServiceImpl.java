package com.example.socialhelp.services;

import com.example.socialhelp.dto.SpecialistDto;
import com.example.socialhelp.dto.SpecializationDto;
import com.example.socialhelp.models.Specialization;
import com.example.socialhelp.repositories.SpecializationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationsRepository specializationsRepository;

    @Override
    public List<SpecializationDto> findAll() {
        List<Specialization> specialization = specializationsRepository.findAll();
        List<SpecializationDto> specializationDtos = new ArrayList<>();
        if (!specialization.isEmpty()){
            for (int i = 0; i < specialization.size(); i++) {
                specializationDtos.add(SpecializationDto.mapSpecialization(specialization.get(i)));
            }
        }
        return specializationDtos;
    }
}

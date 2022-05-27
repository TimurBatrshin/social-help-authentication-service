package com.example.socialhelp.services;

import com.example.socialhelp.dto.SpecialistDto;
import com.example.socialhelp.dto.UserDto;
import com.example.socialhelp.models.AdditionalInfoForSpecialist;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.AdditionalInfoForSpecialistRepository;
import com.example.socialhelp.repositories.SpecializationsRepository;
import com.example.socialhelp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdditionalInfoForSpecialistServiceImpl implements AdditionalInfoForSpecialistService {

    @Autowired
    private AdditionalInfoForSpecialistRepository additionalInfoForSpecialistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SpecializationsRepository specializationsRepository;

    @Override
    public List<SpecialistDto> findAll() {
        List<AdditionalInfoForSpecialist> additionalInfoForSpecialists = additionalInfoForSpecialistRepository.findAll();
        List<SpecialistDto> specialistDtos = new ArrayList<>();
        for (int i = 0; i < additionalInfoForSpecialists.size(); i++) {
            SpecialistDto specialistDto = SpecialistDto.builder()
                    .specialization(additionalInfoForSpecialists.get(i).getSpecialization().getSpecializationType())
                    .avatarUrl(additionalInfoForSpecialists.get(i).getUser().getAvatarUrl())
                    .firstName(additionalInfoForSpecialists.get(i).getUser().getFirstName())
                    .lastName(additionalInfoForSpecialists.get(i).getUser().getLastName())
                    .build();
            specialistDtos.add(specialistDto);
        }
//        List<SpecialistDto> specialistDtos = new ArrayList<>();
//        for (int i = 0; i < additionalInfoForSpecialists.size(); i++) {
//            Optional<User> user = userRepository.findById(additionalInfoForSpecialists.get(i).getUser().getId());
//            SpecialistDto specialistDto = SpecialistDto.builder()
//                    .firstName(user.get().getFirstName())
//                    .lastName(user.get().getLastName())
//                    .avatarUrl(user.get().getAvatarUrl())
//                    .specialization(specializationsRepository.findSpecializationByAdditionalInfoForSpecialist(additionalInfoForSpecialists.get(i)).getSpecializationType())
//                    .build();
//            specialistDtos.add(specialistDto);
//        }
        return specialistDtos;
    }
}

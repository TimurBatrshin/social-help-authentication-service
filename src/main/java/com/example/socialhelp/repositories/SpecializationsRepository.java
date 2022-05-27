package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpecializationsRepository extends JpaRepository<Specialization, Long> {
    Specialization findSpecializationBySpecializationType(String name);

    @Query(value = "select s from Specialization s join Problem p on s.id = p.specialization.id join AdditionalInfoForSpecialist aifs on s.id = aifs.specialization.id join User u on u.id = aifs.user.id where p.id =:problemId")
    List<Specialization> findSpecializationByProblemId(Long problemId);

    @Override
    List<Specialization> findAll();

//    Specialization findSpecializationByAdditionalInfoForSpecialist(AdditionalInfoForSpecialist additionalInfoForSpecialists);
}

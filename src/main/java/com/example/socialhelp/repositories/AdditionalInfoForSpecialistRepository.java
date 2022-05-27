package com.example.socialhelp.repositories;

import com.example.socialhelp.models.AdditionalInfoForSpecialist;
import com.example.socialhelp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdditionalInfoForSpecialistRepository extends JpaRepository<AdditionalInfoForSpecialist, Long> {
    @Query(value = "select aifs from AdditionalInfoForSpecialist aifs join User u on u.id = aifs.user.id join Specialization s on s.id = aifs.specialization.id join Problem p on s.id = p.specialization.id where p.id =:problem_id")
    List<AdditionalInfoForSpecialist> findByProblemId(Long problem_id);


}

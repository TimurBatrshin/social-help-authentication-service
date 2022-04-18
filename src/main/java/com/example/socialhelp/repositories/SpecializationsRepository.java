package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationsRepository extends JpaRepository<Specialization, Long> {

    List<Specialization> findAllBySpecializations(String name);
}

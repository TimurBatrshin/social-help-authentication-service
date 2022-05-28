package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    Optional<Problem> findById(Long id);
    Problem findByPhotoUrl(String name);

}

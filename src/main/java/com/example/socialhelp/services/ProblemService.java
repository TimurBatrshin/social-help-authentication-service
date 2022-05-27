package com.example.socialhelp.services;

import com.example.socialhelp.models.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProblemService {
    Optional<Problem> getProblemById(Long id);

    List<Problem> findAll();
}

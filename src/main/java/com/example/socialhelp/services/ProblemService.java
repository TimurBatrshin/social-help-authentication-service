package com.example.socialhelp.services;

import com.example.socialhelp.models.Problem;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProblemService {
    Optional<Problem> getProblemById(Long id);

    List<Problem> findAll() throws IOException;
    Problem findByName(String name);
}

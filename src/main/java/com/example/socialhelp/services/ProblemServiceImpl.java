package com.example.socialhelp.services;

import com.example.socialhelp.models.Problem;
import com.example.socialhelp.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public Optional<Problem> getProblemById(Long id) {
        return problemRepository.findById(id);
    }

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }
}

package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.ProblemUserDto;
import com.example.socialhelp.dto.SpecialistDto;
import com.example.socialhelp.models.Problem;
import com.example.socialhelp.services.AdditionalInfoForSpecialistService;
import com.example.socialhelp.services.FileService;
import com.example.socialhelp.services.ProblemService;
import com.example.socialhelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class ProblemController {

    @Autowired
    private ProblemService problemService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    @Autowired
    private AdditionalInfoForSpecialistService additionalInfoForSpecialistService;

    @GetMapping("/allProblem")
    public ResponseEntity<?> getProblem() throws IOException {
        List<Problem> problem = problemService.findAll();
        return ResponseEntity.ok(problem);
    }

    @GetMapping("/problem/{id}")
    public ResponseEntity<?> getOneProblem(@PathVariable Long id) {
        List<ProblemUserDto> userDto = userService.findSpecialistByProblemId(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/problem/allSpecialist")
    public ResponseEntity<?> getAllSpecialist() {
        List<SpecialistDto> specialistDtos = additionalInfoForSpecialistService.findAll();
        return ResponseEntity.ok(specialistDtos);
    }

}

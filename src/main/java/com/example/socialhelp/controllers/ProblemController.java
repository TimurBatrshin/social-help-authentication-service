package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.ProblemUserDto;
import com.example.socialhelp.dto.SpecialistDto;
import com.example.socialhelp.models.AdditionalInfoForSpecialist;
import com.example.socialhelp.models.Problem;
import com.example.socialhelp.models.User;
import com.example.socialhelp.services.AdditionalInfoForSpecialistService;
import com.example.socialhelp.services.ProblemService;
import com.example.socialhelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdditionalInfoForSpecialistService additionalInfoForSpecialistService;

    @GetMapping("/allProblem")
    public ResponseEntity<?> getProblem() {
        List<Problem> problem = problemService.findAll();
        Problem[] problems = new Problem[problem.size()];
        for (int i = 0; i < problem.size(); i++) {
            problems[i] = problem.get(i);
        }
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/problem/{id}")
    public ResponseEntity<?> getOneProblem(@PathVariable Long id) {
        List<ProblemUserDto> userDto = userService.findSpecialistByProblemId(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/problem/allSpecialist")
    public ResponseEntity<?> getAllSpecialist(){
        List<SpecialistDto> specialistDtos = additionalInfoForSpecialistService.findAll();
        return ResponseEntity.ok(specialistDtos);
    }

    @GetMapping("/t")
    public String getA(){
        return "ffbv";
    }

}

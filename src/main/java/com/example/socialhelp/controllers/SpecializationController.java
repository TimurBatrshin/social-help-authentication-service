package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.ProblemUserDto;
import com.example.socialhelp.services.SpecializationService;
import com.example.socialhelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@CrossOrigin
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private UserService userService;

    @GetMapping("/AllSpecialization")
    @PermitAll
    public ResponseEntity<?> getAllSpecializations(){
        return ResponseEntity.ok(specializationService.findAll());
    }

    @GetMapping("/specialization/{id}")
    @PermitAll
    public ResponseEntity<?> getSpecialistBySpecializationById(@PathVariable Long id){
        List<ProblemUserDto> problemUserDtos = userService.findUserBySpecializationId(id);
        return ResponseEntity.ok(problemUserDtos);
    }

}

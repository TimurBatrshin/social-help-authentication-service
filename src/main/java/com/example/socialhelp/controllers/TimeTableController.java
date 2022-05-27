package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.TimeTableDto;
import com.example.socialhelp.dto.UserDto;
import com.example.socialhelp.services.TimeTableService;
import com.example.socialhelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class TimeTableController {

    @Autowired
    private UserService userService;

    @Autowired
    private TimeTableService timeTableService;

    @PostMapping("/setTimeTable/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> setTimeTableToUser(@PathVariable Long id,@RequestBody TimeTableDto timeTableDto, HttpServletRequest httpServletRequest){
        UserDto user = userService.findUserByAccessToken(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        System.out.println(timeTableDto);
        timeTableService.addTimeTable(id, user.getId(), timeTableDto);
        return ResponseEntity.ok(timeTableDto);
    }

    @GetMapping("/timeTable")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getTimeTable(HttpServletRequest httpServletRequest){
        UserDto user = userService.findUserByAccessToken(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return ResponseEntity.ok(timeTableService.getTimeTable(user.getId()));
    }
}

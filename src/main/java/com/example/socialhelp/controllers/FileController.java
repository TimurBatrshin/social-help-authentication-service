package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.UserDto;
import com.example.socialhelp.services.FileService;
import com.example.socialhelp.services.ProblemService;
import com.example.socialhelp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProblemService problemService;

    @PostMapping("/files")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws IOException {
        UserDto user = userService.findUserByAccessToken(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        String filePath = fileService.save(file, user.getId());
        return ResponseEntity.ok()
                .body(filePath);
    }

    @GetMapping("/getFiles")
    @PreAuthorize("isAuthenticated()")
    public void getFile(HttpServletResponse response, HttpServletRequest httpServletRequest) {
        UserDto user = userService.findUserByAccessToken(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
        fileService.writeFileToResponse(user.getId(), response);
    }


}

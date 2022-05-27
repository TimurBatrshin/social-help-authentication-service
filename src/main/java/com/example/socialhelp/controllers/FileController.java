package com.example.socialhelp.controllers;

import com.example.socialhelp.dto.FileDto;
import com.example.socialhelp.models.File;
import com.example.socialhelp.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files/upload")
    public ResponseEntity<?> getFilesUpload() {
        return null;
    }

    @PostMapping("files")
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String filePath = fileService.save(file);
        return ResponseEntity.ok()
                .body(filePath);
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response) {
        fileService.writeFileToResponse(fileName, response);
    }

}

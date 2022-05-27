package com.example.socialhelp.services;

import com.example.socialhelp.models.File;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface FileService {
    String save(MultipartFile multipartFile) throws IOException;
    Optional<File> getFile(Long id);
    List<File> getAllFiles();
    void writeFileToResponse(String fileName, HttpServletResponse response);
}

package com.example.socialhelp.services;

import com.example.socialhelp.models.File;
import com.example.socialhelp.repositories.FileRepository;
import com.example.socialhelp.repositories.UserRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String save(MultipartFile multipartFile, Long id) throws IOException {
//        File file = new File();
//        file.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
//        file.setContentType(multipartFile.getContentType());
////        file.setData(multipartFile.getBytes());
//        file.setSize(multipartFile.getSize());
        String storageName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        File file = File.builder()
                .type(multipartFile.getContentType())
                .originalFilename(multipartFile.getOriginalFilename())
                .size(multipartFile.getSize())
                .storageFileName(storageName)
                .url(storagePath + "\\" + storageName)
                .user(userRepository.findUserById(id))
                .build();

        Files.copy(multipartFile.getInputStream(), Paths.get(storagePath, storageName));
        fileRepository.save(file);

        return file.getStorageFileName();
    }

    @Override
    public Optional<File> getFile(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public void writeFileToResponse(Long id, HttpServletResponse response) {
        File file = fileRepository.findByUserId(id);
        response.setContentType(file.getType());
        try {
            IOUtils.copy(new FileInputStream(file.getUrl()), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}

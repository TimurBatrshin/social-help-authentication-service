package com.example.socialhelp.repositories;

import com.example.socialhelp.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByUserId(Long id);
}

package com.example.socialhelp.repositories;

import com.example.socialhelp.models.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    TimeTable findByUserId(Long id);
}

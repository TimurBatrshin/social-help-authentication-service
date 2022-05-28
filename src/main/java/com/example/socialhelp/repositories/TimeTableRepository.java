package com.example.socialhelp.repositories;

import com.example.socialhelp.models.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    @Query(value = "select tt from TimeTable tt where tt.user.id = :id or tt.specialist.id = :id")
    List<TimeTable> getTimeTable(Long id);
}

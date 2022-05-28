package com.example.socialhelp.services;

import com.example.socialhelp.dto.TimeTableDto;
import com.example.socialhelp.dto.TimeTableUsersDto;
import com.example.socialhelp.models.TimeTable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TimeTableService {
    void addTimeTable(Long userId, Long specId, TimeTableDto timeTableDto);

    List<TimeTableUsersDto> getTimeTable(Long id);
}

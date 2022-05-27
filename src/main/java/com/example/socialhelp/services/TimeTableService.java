package com.example.socialhelp.services;

import com.example.socialhelp.dto.TimeTableDto;
import com.example.socialhelp.dto.TimeTableUsersDto;
import com.example.socialhelp.models.TimeTable;
import org.springframework.stereotype.Service;


public interface TimeTableService {
    void addTimeTable(Long userId, Long specId, TimeTableDto timeTableDto);

    TimeTableUsersDto getTimeTable(Long id);
}

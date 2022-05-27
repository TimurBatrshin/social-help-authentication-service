package com.example.socialhelp.services;

import com.example.socialhelp.dto.TimeTableDto;
import com.example.socialhelp.dto.TimeTableUsersDto;
import com.example.socialhelp.models.TimeTable;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.TimeTableRepository;
import com.example.socialhelp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addTimeTable(Long userId, Long specId, TimeTableDto timeTableDto) {
        User user = userRepository.findUserById(userId);
        User spec = userRepository.findUserById(specId);

        TimeTable timeTable = TimeTable.builder()
                .description(timeTableDto.getDescription())
                .timeStamp(timeTableDto.getTimeStamp())
                .user(user)
                .specialist(spec)
                .build();
        timeTableRepository.save(timeTable);


    }

    @Override
    public TimeTableUsersDto getTimeTable(Long id) {
        TimeTable timeTable = timeTableRepository.findByUserId(id);
        return TimeTableUsersDto.builder()
                .description(timeTable.getDescription())
                .firstNameSpec(timeTable.getSpecialist().getFirstName())
                .lastNameSpec(timeTable.getSpecialist().getLastName())
                .firstNameUser(timeTable.getUser().getFirstName())
                .lastNameUser(timeTable.getUser().getLastName())
                .timeStamp(timeTable.getTimeStamp())
                .build();
    }
}

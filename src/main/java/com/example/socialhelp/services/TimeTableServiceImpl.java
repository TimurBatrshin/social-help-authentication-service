package com.example.socialhelp.services;

import com.example.socialhelp.dto.TimeTableDto;
import com.example.socialhelp.dto.TimeTableUsersDto;
import com.example.socialhelp.models.TimeTable;
import com.example.socialhelp.models.User;
import com.example.socialhelp.repositories.TimeTableRepository;
import com.example.socialhelp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<TimeTableUsersDto> getTimeTable(Long id) {
        List<TimeTable> timeTable = timeTableRepository.getTimeTable(id);
        List<TimeTableUsersDto> timeTableUsersDtos = new ArrayList<>();
        for (int i = 0; i < timeTable.size(); i++) {
            TimeTableUsersDto timeTableUsersDto = TimeTableUsersDto.builder()
                    .description(timeTable.get(i).getDescription())
                    .firstNameSpec(timeTable.get(i).getSpecialist().getFirstName())
                    .lastNameSpec(timeTable.get(i).getSpecialist().getLastName())
                    .firstNameUser(timeTable.get(i).getUser().getFirstName())
                    .lastNameUser(timeTable.get(i).getUser().getLastName())
                    .timeStamp(timeTable.get(i).getTimeStamp())
                    .build();
            timeTableUsersDtos.add(timeTableUsersDto);

        }
        return timeTableUsersDtos;
    }
}

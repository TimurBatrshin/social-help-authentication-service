package com.example.socialhelp.dto;

import com.example.socialhelp.models.TimeTable;
import com.example.socialhelp.models.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TimeTableDto {
    private Long id;
    private Long timeStamp;
    private String description;
    private User user;
    private User spec;

    public static TimeTableDto mapTimeTable(TimeTable timeTable){
        return TimeTableDto.builder()
                .id(timeTable.getId())
                .timeStamp(timeTable.getTimeStamp())
                .description(timeTable.getDescription())
                .user(timeTable.getUser())
                .spec(timeTable.getSpecialist())
                .build();

    }
}

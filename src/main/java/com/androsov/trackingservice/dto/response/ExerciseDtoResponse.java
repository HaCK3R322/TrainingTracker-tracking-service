package com.androsov.trackingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDtoResponse {
    private Long id;
    private String name;
    private String units;
    private Long trainingId;
    private Timestamp timestamp;
}

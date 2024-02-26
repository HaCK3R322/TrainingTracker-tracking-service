package com.androsov.trackingservice.dto.response.exercise;

import com.androsov.trackingservice.dto.response.SetDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseWithSetsDtoResponse {
    private ExerciseDtoResponse exercise;
    private List<SetDtoResponse> sets;
}

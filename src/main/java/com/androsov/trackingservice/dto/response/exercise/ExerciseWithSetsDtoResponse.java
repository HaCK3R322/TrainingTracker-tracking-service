package com.androsov.trackingservice.dto.response.exercise;

import com.androsov.trackingservice.dto.response.SetDtoResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExerciseWithSetsDtoResponse {
    private ExerciseDtoResponse exercise;
    private List<SetDtoResponse> sets;
}

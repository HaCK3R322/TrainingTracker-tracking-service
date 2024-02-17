package com.androsov.trackingservice.dto.response.exercise;

import com.androsov.trackingservice.dto.response.set.SetDtoResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
public class ExerciseWithSetsDto {
    private ExerciseDtoResponse exercise;
    private List<SetDtoResponse> sets;
}

package com.androsov.trackingservice.dto.response.set;


import com.androsov.trackingservice.dto.response.exercise.ExerciseWithSetsDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetAllSetsByTrainingIdResponse {
    private List<ExerciseWithSetsDto> exercises;
}

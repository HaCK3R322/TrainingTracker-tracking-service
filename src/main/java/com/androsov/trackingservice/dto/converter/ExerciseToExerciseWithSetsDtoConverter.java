package com.androsov.trackingservice.dto.converter;

import com.androsov.trackingservice.dto.response.SetDtoResponse;
import com.androsov.trackingservice.dto.response.exercise.ExerciseDtoResponse;
import com.androsov.trackingservice.dto.response.exercise.ExerciseWithSetsDtoResponse;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExerciseToExerciseWithSetsDtoConverter {
    private final ExerciseToDtoConverter exerciseToDtoConverter;
    private final SetToDtoConverter setToDtoConverter;

    public ExerciseWithSetsDtoResponse convert(Exercise sourceExercise, List<Set> sourceSets) {
        ExerciseDtoResponse exerciseDto = exerciseToDtoConverter.convert(sourceExercise);
        List<SetDtoResponse> setsDtoResponseList = setToDtoConverter.convert(sourceSets);

        ExerciseWithSetsDtoResponse exerciseWithSetsDto = new ExerciseWithSetsDtoResponse();
        exerciseWithSetsDto.setExercise(exerciseDto);
        exerciseWithSetsDto.setSets(setsDtoResponseList);

        return exerciseWithSetsDto;
    }

    public List<ExerciseWithSetsDtoResponse> convert(Map<Exercise, List<Set>> map) {
        List<ExerciseWithSetsDtoResponse> response = new ArrayList<>();
        map.keySet().forEach(exercise -> {
            response.add(convert(exercise, map.get(exercise)));
        });

        return response;
    }
}

package com.androsov.trackingservice.dto.converter;

import com.androsov.trackingservice.dto.response.exercise.ExerciseDtoResponse;
import com.androsov.trackingservice.dto.response.exercise.ExerciseWithSetsDto;
import com.androsov.trackingservice.dto.response.set.GetAllSetsByTrainingIdResponse;
import com.androsov.trackingservice.dto.response.set.SetDtoResponse;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class SetsToAllSetsByTrainingIdDtoResponseConverter implements Converter<List<Set>, GetAllSetsByTrainingIdResponse> {
    private final SetToDtoConverter setToDtoConverter;
    private final ExerciseToDtoConverter exerciseToDtoConverter;

    @Override
    public GetAllSetsByTrainingIdResponse convert(List<Set> sets) {
        GetAllSetsByTrainingIdResponse response = new GetAllSetsByTrainingIdResponse();
        response.setExercises(new ArrayList<>());

        Map<ExerciseDtoResponse, List<SetDtoResponse>> map = new HashMap<>();

        for (Set set: sets) {
            ExerciseDtoResponse exerciseDto = exerciseToDtoConverter.convert(set.getExercise());

            if(!map.containsKey(exerciseDto)) {
                map.put(exerciseDto, new ArrayList<>());
            }

            SetDtoResponse setDto = setToDtoConverter.convert(set);
            map.get(exerciseDto).add(setDto);
        }

        for (ExerciseDtoResponse exerciseDto : map.keySet()) {
            ExerciseWithSetsDto exerciseWithSetsDto = new ExerciseWithSetsDto();
            exerciseWithSetsDto.setExercise(exerciseDto);
            exerciseWithSetsDto.setSets(map.get(exerciseDto));

            response.getExercises().add(exerciseWithSetsDto);
        }

        return response;
    }
}

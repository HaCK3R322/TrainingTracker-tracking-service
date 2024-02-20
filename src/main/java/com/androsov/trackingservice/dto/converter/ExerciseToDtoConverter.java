package com.androsov.trackingservice.dto.converter;

import com.androsov.trackingservice.dto.response.ExerciseDtoResponse;
import com.androsov.trackingservice.entity.Exercise;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseToDtoConverter implements Converter<Exercise, ExerciseDtoResponse> {
    @Override
    public ExerciseDtoResponse convert(Exercise source) {
        ExerciseDtoResponse response = new ExerciseDtoResponse();
        response.setId(source.getId());
        response.setName(source.getName());
        response.setUnits(source.getUnits());
        response.setTrainingId(source.getTraining().getId());
        response.setTimestamp(source.getTimestamp());

        return response;
    }

    public List<ExerciseDtoResponse> convert(List<Exercise> sources) {
        List<ExerciseDtoResponse> response = new ArrayList<>();
        sources.forEach(exercise -> response.add(convert(exercise)));
        return response;
    }
}

package com.androsov.trackingservice.dto.converter;

import com.androsov.trackingservice.dto.response.TrainingDtoResponse;
import com.androsov.trackingservice.entity.Training;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Component
public class TrainingToDtoConverter implements Converter<Training, TrainingDtoResponse> {
    @Override
    public TrainingDtoResponse convert(Training source) {
        TrainingDtoResponse response = new TrainingDtoResponse();
        response.setId(source.getId());
        response.setName(source.getName());
        response.setUserId(source.getUser().getId());

        return response;
    }

    public List<TrainingDtoResponse> convert(List<Training> sources) {
        List<TrainingDtoResponse> response = new ArrayList<>();
        sources.forEach(training -> response.add(convert(training)));
        return response;
    }
}

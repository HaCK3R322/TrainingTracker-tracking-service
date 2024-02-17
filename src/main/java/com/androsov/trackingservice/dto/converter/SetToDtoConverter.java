package com.androsov.trackingservice.dto.converter;

import com.androsov.trackingservice.dto.response.set.SetDtoResponse;
import com.androsov.trackingservice.entity.Set;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SetToDtoConverter implements Converter<Set, SetDtoResponse> {
    @Override
    public SetDtoResponse convert(Set source) {
        SetDtoResponse response = new SetDtoResponse();
        response.setId(source.getId());
        response.setReps(source.getReps());
        response.setAmount(source.getAmount());
        response.setExerciseId(source.getExercise().getId());

        return response;
    }

    public List<SetDtoResponse> convert(List<Set> sources) {
        List<SetDtoResponse> response = new ArrayList<>();
        sources.forEach(set -> response.add(convert(set)));
        return response;
    }
}

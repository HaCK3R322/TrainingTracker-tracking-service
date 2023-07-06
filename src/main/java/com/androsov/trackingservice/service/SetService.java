package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.SetCreateRequest;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetService {
    @Autowired
    private SetRepository setRepository;

    public Set createAndSaveFromRequest(SetCreateRequest request) {
        Set set = new Set();
        set.setAmount(request.getAmount());
        set.setReps(request.getReps());

        Exercise exercise = new Exercise();
        exercise.setId(request.getExerciseId());
        set.setExercise(exercise);

        return setRepository.save(set);
    }
}

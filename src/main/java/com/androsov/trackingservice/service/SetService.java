package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.request.SetCreateRequest;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.repository.SetRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Set> findAllByExerciseId(Long exerciseId) throws NotFoundException {
        List<Set> sets = setRepository.findAllByExerciseId(exerciseId);

        if(sets.size() < 1)
            throw new NotFoundException("Sets with exercise id " + String.valueOf(exerciseId) + " not found!");

        return sets;
    }
}

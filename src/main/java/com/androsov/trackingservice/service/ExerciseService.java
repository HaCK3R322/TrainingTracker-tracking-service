package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.request.ExerciseCreateRequest;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private UserService userService;

    public Exercise createAndSaveFromRequest(ExerciseCreateRequest request) {
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setTrainingName(request.getTrainingName());
        exercise.setUnits(request.getUnits());
        exercise.setUser(userService.getUserFromSecurityContext());

        return exerciseRepository.save(exercise);
    }
}

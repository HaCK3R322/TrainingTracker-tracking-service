package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.request.ExerciseCreateRequest;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.repository.ExerciseRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TrainingService trainingService;

    public Exercise createAndSaveFromRequest(ExerciseCreateRequest request) throws NotFoundException {
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setUnits(request.getUnits());
        exercise.setTraining(trainingService.getById(request.getTrainingId()));

        return exerciseRepository.save(exercise);
    }

    public Exercise findById(Long id) throws NotFoundException {
        Optional<Exercise> exercise = exerciseRepository.findById(id);

        if(exercise.isEmpty())
            throw new NotFoundException("Could not find exercise with id " + id);

        return exercise.get();
    }

    public List<Exercise> findAllByTrainingId(Long trainingId) {
        return exerciseRepository.findAllByTrainingId(trainingId);
    }
}

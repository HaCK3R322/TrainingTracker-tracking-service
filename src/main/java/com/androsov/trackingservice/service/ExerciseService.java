package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.request.ExerciseCreateRequest;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Training;
import com.androsov.trackingservice.entity.User;
import com.androsov.trackingservice.exception.NotFoundException;
import com.androsov.trackingservice.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private UserService userService;

    public Exercise createAndSaveFromRequest(ExerciseCreateRequest request) throws NotFoundException, AccessDeniedException {
        Exercise exercise = new Exercise();
        exercise.setName(request.getName());
        exercise.setUnits(request.getUnits());
        exercise.setTraining(trainingService.getById(request.getTrainingId()));
        exercise.setTimestamp(request.getTimestamp());

        return exerciseRepository.save(exercise);
    }

    public Exercise getById(Long id) throws NotFoundException, AccessDeniedException {
        Optional<Exercise> exercise = exerciseRepository.findById(id);

        if (exercise.isEmpty())
            throw new NotFoundException("Could not find exercise with id " + id);

        if (!isCurrentUserOwnsExercise(exercise.get()))
            throw new AccessDeniedException("Access to training with id " + id + " denied");

        return exercise.get();
    }

    public List<Exercise> findAllByTrainingId(Long trainingId) {
        Training training = trainingService.getById(trainingId);

        return exerciseRepository.findAllByTraining(training);
    }

    public boolean isCurrentUserOwnsExercise(Exercise exercise) {
        User currentUser = userService.getUserFromSecurityContext();
        User exercisesUser = exercise.getTraining().getUser();

        return currentUser.getId().equals(exercisesUser.getId());
    }

    public Exercise deleteById(Long exerciseId) throws NotFoundException, AccessDeniedException {
        Exercise exercise = getById(exerciseId);

        exerciseRepository.deleteById(exerciseId);

        return exercise;
    }
}

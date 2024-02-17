package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.request.SetCreateRequest;
import com.androsov.trackingservice.dto.request.SetPatchRequest;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.exception.NotFoundException;
import com.androsov.trackingservice.repository.SetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SetService {
    @Autowired
    private SetRepository setRepository;

    @Autowired
    private ExerciseService exerciseService;

    public Set createAndSaveFromRequest(SetCreateRequest request) throws NotFoundException, AccessDeniedException {
        Set set = new Set();
        set.setAmount(request.getAmount());
        set.setReps(request.getReps());
        set.setExercise(exerciseService.getById(request.getExerciseId()));

        return setRepository.save(set);
    }

    public List<Set> findAllByExerciseId(Long exerciseId) throws NotFoundException, AccessDeniedException {
        Exercise exercise = exerciseService.getById(exerciseId);
        List<Set> sets = setRepository.findAllByExercise(exercise);

        return sets;
    }

    public List<Set> findAllByTrainingId(Long trainingId) throws NotFoundException, AccessDeniedException {
        List<Exercise> exercises = exerciseService.findAllByTrainingId(trainingId);

        List<Set> sets = new ArrayList<>();
        for (Exercise exercise: exercises) {
            sets.addAll(findAllByExerciseId(exercise.getId()));
        }

        return sets;
    }

    public Set patchSet(SetPatchRequest request) throws NotFoundException, AccessDeniedException {
        Optional<Set> setOptional = setRepository.findById(request.getId());

        if(setOptional.isEmpty())
            throw new NotFoundException("Cannot patch set with id " + request.getId() + ": NotFound");

        Set updatedSet = setOptional.get();
        updatedSet.setReps(request.getReps());
        updatedSet.setAmount(request.getAmount());

        return setRepository.save(updatedSet);
    }

    public void deleteSetsByExerciseId(Long exerciseId) throws NotFoundException, AccessDeniedException {
        Exercise exercise = exerciseService.getById(exerciseId);
        List<Set> sets = setRepository.findAllByExercise(exercise);
        setRepository.deleteAll(sets);
    }

    public void deleteSetById(Long setId) throws NotFoundException, AccessDeniedException {
        Optional<Set> setOptional = setRepository.findById(setId);

        if(setOptional.isEmpty())
            throw new NotFoundException("Cannot patch set with id " + setId + ": NotFound");

        setRepository.delete(setOptional.get());
    }
}

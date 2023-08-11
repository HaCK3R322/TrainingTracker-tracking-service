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

import java.sql.Timestamp;
import java.util.Date;
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

        set.setTimestamp(new Timestamp(new Date().getTime()));

        return setRepository.save(set);
    }

    public List<Set> findAllByExerciseId(Long exerciseId) throws NotFoundException, AccessDeniedException {
        Exercise exercise = exerciseService.getById(exerciseId);
        List<Set> sets = setRepository.findAllByExercise(exercise);

        if (sets.size() < 1)
            throw new NotFoundException("Sets with exercise id " + exerciseId + " not found!");

        return sets;
    }

    public Set patchSet(SetPatchRequest request) throws NotFoundException, AccessDeniedException {
        Optional<Set> setOptional = setRepository.findById(request.getId());

        if(setOptional.isEmpty())
            throw new NotFoundException("Cannot patch set with id " + request.getId() + ": NotFound");

        Set updatedSet = setOptional.get();
        updatedSet.setReps(request.getReps());
        updatedSet.setAmount(request.getAmount());
        updatedSet.setTimestamp(request.getTimestamp());

        return setRepository.save(updatedSet);
    }
}

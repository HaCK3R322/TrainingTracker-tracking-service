package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.request.SetCreateRequest;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.entity.User;
import com.androsov.trackingservice.repository.SetRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class SetService {
    @Autowired
    private SetRepository setRepository;

    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private UserService userService;

    public Set createAndSaveFromRequest(SetCreateRequest request) throws NotFoundException {
        Set set = new Set();
        set.setAmount(request.getAmount());
        set.setReps(request.getReps());
        set.setExercise(exerciseService.findById(request.getExerciseId()));

        set.setTimestamp(new Timestamp(new Date().getTime()));

        return setRepository.save(set);
    }

    public List<Set> findAllByExerciseId(Long exerciseId) throws NotFoundException {
        List<Set> sets = setRepository.findAllByExerciseId(exerciseId);

        if(sets.size() < 1)
            throw new NotFoundException("Sets with exercise id " + exerciseId + " not found!");

        return sets;
    }

    public boolean isSetBelongsToCurrentUser(Set set) {
        User currentUser = userService.getUserFromSecurityContext();
        User setsUser = set.getExercise().getTraining().getUser();

        return currentUser.equals(setsUser);
    }
}

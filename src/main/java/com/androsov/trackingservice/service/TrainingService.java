package com.androsov.trackingservice.service;

import com.androsov.trackingservice.dto.request.TrainingCreateRequest;
import com.androsov.trackingservice.entity.Training;
import com.androsov.trackingservice.entity.User;
import com.androsov.trackingservice.repository.TrainingRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserService userService;

    public Training createAndSaveFromRequest(TrainingCreateRequest request) {
        Training training = new Training();
        training.setUser(userService.getUserFromSecurityContext());
        training.setName(request.getName());

        return trainingRepository.save(training);
    }

    public Training getById(Long id) throws NotFoundException, AccessDeniedException {
        Optional<Training> training = trainingRepository.findById(id);

        if(training.isEmpty())
            throw new NotFoundException("Could not find training with id " + id);

        if(!isCurrentUserOwnsTraining(training.get()))
            throw new AccessDeniedException("Access denied to training with id " + id);

        return training.get();
    }

    public List<Training> getAllOfCurrentUser() {
        User currentUser = userService.getUserFromSecurityContext();

        return trainingRepository.getAllByUser(currentUser);
    }

    public boolean isCurrentUserOwnsTraining(Training training) {
        User currentUser = userService.getUserFromSecurityContext();

        return currentUser.getId().equals(training.getUser().getId());
    }
}

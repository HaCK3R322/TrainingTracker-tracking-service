package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.request.TrainingCreateRequest;
import com.androsov.trackingservice.dto.response.TrainingDtoResponse;
import com.androsov.trackingservice.entity.Training;
import com.androsov.trackingservice.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/tracking/trainings")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @PostMapping
    public ResponseEntity<TrainingDtoResponse> createTraining(@RequestBody TrainingCreateRequest request) {
        Training training = trainingService.createAndSaveFromRequest(request);
        TrainingDtoResponse trainingDtoResponse = new TrainingDtoResponse();
        trainingDtoResponse.setId(training.getId());
        trainingDtoResponse.setName(training.getName());
        trainingDtoResponse.setUserId(training.getUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(trainingDtoResponse);
    }
}

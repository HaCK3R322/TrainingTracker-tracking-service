package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.converter.TrainingToDtoConverter;
import com.androsov.trackingservice.dto.request.TrainingCreateRequest;
import com.androsov.trackingservice.dto.response.TrainingDtoResponse;
import com.androsov.trackingservice.entity.Training;
import com.androsov.trackingservice.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tracking/trainings")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private TrainingToDtoConverter trainingToDtoConverter;

    @PostMapping
    public ResponseEntity<TrainingDtoResponse> createTraining(@RequestBody TrainingCreateRequest request) {
        Training training = trainingService.createAndSaveFromRequest(request);

        TrainingDtoResponse trainingDtoResponse = trainingToDtoConverter.convert(training);

        return ResponseEntity.status(HttpStatus.CREATED).body(trainingDtoResponse);
    }

    @GetMapping
    public ResponseEntity<List<TrainingDtoResponse>> getAllTrainings() {
        List<Training> trainings = trainingService.getAllOfCurrentUser();

        List<TrainingDtoResponse> trainingDtos = trainingToDtoConverter.convert(trainings);

        return ResponseEntity.status(HttpStatus.OK).body(trainingDtos);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<TrainingDtoResponse> deleteTrainingById(@RequestParam Long id) {
        Training deletedTraining = trainingService.deleteTrainingById(id);

        TrainingDtoResponse trainingDtoResponse = trainingToDtoConverter.convert(deletedTraining);

        return ResponseEntity.status(HttpStatus.OK).body(trainingDtoResponse);
    }
}

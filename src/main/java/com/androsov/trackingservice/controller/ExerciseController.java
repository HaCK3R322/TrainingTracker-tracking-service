package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.request.ExerciseCreateRequest;
import com.androsov.trackingservice.dto.response.ExerciseDtoResponse;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tracking/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseDtoResponse> createExercise(@RequestBody ExerciseCreateRequest request) {
        Exercise createdExercise = exerciseService.createAndSaveFromRequest(request);
        ExerciseDtoResponse response = new ExerciseDtoResponse();
        response.setId(createdExercise.getId());
        response.setName(createdExercise.getName());
        response.setUnits(createdExercise.getUnits());
        response.setTrainingId(createdExercise.getTraining().getId());


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.converter.ExerciseToDtoConverter;
import com.androsov.trackingservice.dto.request.ExerciseCreateRequest;
import com.androsov.trackingservice.dto.response.ExerciseDtoResponse;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tracking/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private ExerciseToDtoConverter exerciseToDtoConverter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExerciseDtoResponse> createExercise(@RequestBody ExerciseCreateRequest request) {
        Exercise createdExercise = exerciseService.createAndSaveFromRequest(request);
        ExerciseDtoResponse response = exerciseToDtoConverter.convert(createdExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(params = "trainingId")
    public ResponseEntity<List<ExerciseDtoResponse>> getAllByTrainingId(@RequestParam Long trainingId) {
        List<Exercise> exercises = exerciseService.findAllByTrainingId(trainingId);
        List<ExerciseDtoResponse> exerciseDtoResponses = exerciseToDtoConverter.convert(exercises);
        return ResponseEntity.status(HttpStatus.OK).body(exerciseDtoResponses);
    }
}

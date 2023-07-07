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

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(params = "trainingId")
    public ResponseEntity<List<ExerciseDtoResponse>> getAllByTrainingId(@RequestParam Long trainingId) {
        List<Exercise> exercises = exerciseService.findAllByTrainingId(trainingId);
        List<ExerciseDtoResponse> exerciseDtoResponses = new ArrayList<>();

        exercises.forEach(exercise -> {
            ExerciseDtoResponse response = new ExerciseDtoResponse();
            response.setId(exercise.getId());
            response.setTrainingId(exercise.getTraining().getId());
            response.setName(exercise.getName());
            response.setUnits(exercise.getUnits());

            exerciseDtoResponses.add(response);
        });

        return ResponseEntity.status(HttpStatus.OK).body(exerciseDtoResponses);
    }
}

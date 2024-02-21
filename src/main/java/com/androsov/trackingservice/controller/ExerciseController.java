package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.converter.ExerciseToDtoConverter;
import com.androsov.trackingservice.dto.converter.ExerciseToExerciseWithSetsDtoConverter;
import com.androsov.trackingservice.dto.request.ExerciseCreateRequest;
import com.androsov.trackingservice.dto.response.exercise.ExerciseDtoResponse;
import com.androsov.trackingservice.dto.response.exercise.ExerciseWithSetsDtoResponse;
import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.service.ExerciseService;
import com.androsov.trackingservice.service.SetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/tracking/exercises")
@CrossOrigin
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final SetService setService;

    private final ExerciseToDtoConverter exerciseToDtoConverter;
    private final ExerciseToExerciseWithSetsDtoConverter exerciseToExerciseWithSetsDtoConverter;

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

    @GetMapping(params = {"trainingId", "withSets"})
    public ResponseEntity<List<ExerciseWithSetsDtoResponse>> getAllWithSetsByTrainingId(@RequestParam Long trainingId, @RequestParam Boolean withSets) {
        List<Exercise> exercises = exerciseService.findAllByTrainingId(trainingId);
        Map<Exercise, List<Set>> map = new HashMap<>();

        for(Exercise exercise: exercises) {
            List<Set> sets = setService.findAllByExerciseId(exercise.getId());
            map.put(exercise, sets);
        }

        List<ExerciseWithSetsDtoResponse> response = exerciseToExerciseWithSetsDtoConverter.convert(map);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(params = "exerciseId")
    public ResponseEntity<ExerciseDtoResponse> deleteExerciseById(@RequestParam Long exerciseId) {
        Exercise deletedExercise = exerciseService.deleteById(exerciseId);
        ExerciseDtoResponse response = exerciseToDtoConverter.convert(deletedExercise);
        return ResponseEntity.ok().body(response);
    }
}

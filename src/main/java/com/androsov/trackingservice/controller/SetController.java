package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.request.SetCreateRequest;
import com.androsov.trackingservice.dto.response.SetDtoResponse;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/tracking/sets")
public class SetController {
    @Autowired
    private SetService setService;

    @PostMapping
    public ResponseEntity<SetDtoResponse> createSet(@RequestBody SetCreateRequest request) {
        Set set = setService.createAndSaveFromRequest(request);
        SetDtoResponse setDtoResponse = new SetDtoResponse();
        setDtoResponse.setId(set.getId());
        setDtoResponse.setReps(set.getReps());
        setDtoResponse.setTimestamp(set.getTimestamp());
        setDtoResponse.setAmount(set.getAmount());
        setDtoResponse.setExerciseId(set.getExercise().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(setDtoResponse);
    }

    @GetMapping(params = "exerciseId")
    public ResponseEntity<List<SetDtoResponse>> getSetsByExerciseId(@RequestParam Long exerciseId) {
        List<Set> sets = setService.findAllByExerciseId(exerciseId);
        List<SetDtoResponse> response = new ArrayList<>();

        sets.forEach(set -> {
            SetDtoResponse setDtoResponse = new SetDtoResponse();
            setDtoResponse.setId(set.getId());
            setDtoResponse.setReps(set.getReps());
            setDtoResponse.setTimestamp(set.getTimestamp());
            setDtoResponse.setAmount(set.getAmount());
            setDtoResponse.setExerciseId(set.getExercise().getId());

            response.add(setDtoResponse);
        });

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

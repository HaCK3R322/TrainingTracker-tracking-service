package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.request.SetCreateRequest;
import com.androsov.trackingservice.dto.response.SetResponse;
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
    public ResponseEntity<SetResponse> createSet(@RequestBody SetCreateRequest request) {
        Set set = setService.createAndSaveFromRequest(request);
        SetResponse setResponse = new SetResponse();
        setResponse.setId(set.getId());
        setResponse.setReps(set.getReps());
        setResponse.setTimestamp(set.getTimestamp());
        setResponse.setAmount(set.getAmount());
        setResponse.setExerciseId(set.getExercise().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(setResponse);
    }

    @GetMapping(params = "exerciseId")
    public ResponseEntity<List<SetResponse>> getSetsByExerciseId(@RequestParam Long exerciseId) {
        List<Set> sets = setService.findAllByExerciseId(exerciseId);
        List<SetResponse> response = new ArrayList<>();

        sets.forEach(set -> {
            SetResponse setResponse = new SetResponse();
            setResponse.setId(set.getId());
            setResponse.setReps(set.getReps());
            setResponse.setTimestamp(set.getTimestamp());
            setResponse.setAmount(set.getAmount());
            setResponse.setExerciseId(set.getExercise().getId());

            response.add(setResponse);
        });

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

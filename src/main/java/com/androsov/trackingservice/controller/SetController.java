package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.request.SetCreateRequest;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/tracking/sets")
public class SetController {
    @Autowired
    private SetService setService;

    @PostMapping
    public ResponseEntity<Set> createSet(@RequestBody SetCreateRequest request) {
        Set createdSet = setService.createAndSaveFromRequest(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSet);
    }

    @GetMapping(params = "exerciseId")
    public ResponseEntity<List<Set>> getSetsByExerciseId(@RequestParam Long exerciseId) {
        List<Set> sets = setService.findAllByExerciseId(exerciseId);

        return ResponseEntity.status(HttpStatus.OK).body(sets);
    }
}

package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.converter.SetToDtoConverter;
import com.androsov.trackingservice.dto.request.SetCreateRequest;
import com.androsov.trackingservice.dto.request.SetPatchRequest;
import com.androsov.trackingservice.dto.response.SetDtoResponse;
import com.androsov.trackingservice.entity.Set;
import com.androsov.trackingservice.service.SetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/tracking/sets")
@CrossOrigin
public class SetController {
    @Autowired
    private SetService setService;
    @Autowired
    private SetToDtoConverter setToDtoConverter;

    @PostMapping
    public ResponseEntity<SetDtoResponse> createSet(@RequestBody SetCreateRequest request) {
        Set set = setService.createAndSaveFromRequest(request);
        SetDtoResponse setDtoResponse = setToDtoConverter.convert(set);
        return ResponseEntity.status(HttpStatus.CREATED).body(setDtoResponse);
    }

    @GetMapping(params = "exerciseId")
    public ResponseEntity<List<SetDtoResponse>> getSetsByExerciseId(@RequestParam Long exerciseId) {
        List<Set> sets = setService.findAllByExerciseId(exerciseId);
        List<SetDtoResponse> response = setToDtoConverter.convert(sets);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping
    public ResponseEntity<SetDtoResponse> patchSet(@RequestBody SetPatchRequest request) {
        Set set = setService.patchSet(request);
        SetDtoResponse setDtoResponse = setToDtoConverter.convert(set);
        return ResponseEntity.status(HttpStatus.OK).body(setDtoResponse);
    }

    @DeleteMapping(params = "setId")
    public ResponseEntity<?> deleteSetById(@RequestParam Long setId) {
        setService.deleteSetById(setId);
        return ResponseEntity.ok().build();
    }
}

package com.androsov.trackingservice.controller;

import com.androsov.trackingservice.dto.ExerciseCreateRequest;
import com.androsov.trackingservice.service.ExerciseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.androsov.trackingservice.entity.Exercise;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(path = "/tracking")
public class TrackingController {
    @Autowired
    private ExerciseService exerciseService;

    @PostMapping(path = "/create/exercise", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Exercise> createExercise(@RequestBody ExerciseCreateRequest request) {
        Exercise createdExercise = exerciseService.createAndSaveFromRequest(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdExercise);
    }
}

package com.androsov.trackingservice.controller.advice;

import com.androsov.trackingservice.dto.response.ExceptionMessage;
import com.androsov.trackingservice.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class TrackingAdvice {
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder builder = new StringBuilder();
        ex.getConstraintViolations()
                .forEach(constraintViolation -> {
                    builder.append(constraintViolation.getMessage());
                    builder.append("; ");
                });

        return new ExceptionMessage(builder.toString());
    }

    @ExceptionHandler({NotFoundException.class, AccessDeniedException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage simpleHandling(Exception ex) {
        return new ExceptionMessage(ex.getMessage());
    }
}

package com.androsov.trackingservice.controller.advice;

import com.androsov.trackingservice.dto.response.ExceptionMessage;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionMessage handleNotFoundException(NotFoundException ex) {
        return new ExceptionMessage(ex.getMessage());
    }
}

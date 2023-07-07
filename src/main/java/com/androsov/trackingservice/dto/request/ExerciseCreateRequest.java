package com.androsov.trackingservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCreateRequest {
    private String name;
    private Long trainingId;
    private String units;
}
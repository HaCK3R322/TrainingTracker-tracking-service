package com.androsov.trackingservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCreateRequest {
    public String name;
    public String trainingName;
    public String units;
}

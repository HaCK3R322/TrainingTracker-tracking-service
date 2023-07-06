package com.androsov.trackingservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetCreateRequest {
    public Long exerciseId;
    public Integer amount;
    public Integer reps;
}

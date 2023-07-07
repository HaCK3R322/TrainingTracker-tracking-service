package com.androsov.trackingservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetCreateRequest {
    private Long exerciseId;
    private Integer amount;
    private Integer reps;
}

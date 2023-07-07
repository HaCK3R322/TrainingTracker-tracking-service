package com.androsov.trackingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetDtoResponse {
    private Long id;
    private Integer amount;
    private Integer reps;
    private Timestamp timestamp;
    private Long exerciseId;
}

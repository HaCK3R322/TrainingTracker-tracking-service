package com.androsov.trackingservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Allows to update fields, except for its id and trainingId
 */
@Data
@NoArgsConstructor
public class ExercisePutRequest {
    private Long id;
    private String name;
    private String units;
    private Timestamp timestamp;
}

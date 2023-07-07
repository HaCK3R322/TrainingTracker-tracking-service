package com.androsov.trackingservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDtoResponse {
    private Long id;
    private String name;
    private Long userId;
}

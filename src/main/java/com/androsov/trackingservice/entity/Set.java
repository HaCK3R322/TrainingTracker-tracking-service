package com.androsov.trackingservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "set")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Set {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    @NotNull(message = "Exercise must not be null")
    private Exercise exercise;

    @NotNull
    private Integer reps;

    @NotNull
    private Integer amount;
}

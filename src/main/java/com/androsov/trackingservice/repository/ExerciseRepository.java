package com.androsov.trackingservice.repository;

import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByTraining(Training training);
}
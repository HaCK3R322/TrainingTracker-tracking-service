package com.androsov.trackingservice.repository;

import com.androsov.trackingservice.entity.Exercise;
import com.androsov.trackingservice.entity.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
    List<Set> findAllByExercise(Exercise exercise);
}
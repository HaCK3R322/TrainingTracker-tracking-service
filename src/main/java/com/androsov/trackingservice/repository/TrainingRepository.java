package com.androsov.trackingservice.repository;

import com.androsov.trackingservice.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    boolean existsById(Long id);
}

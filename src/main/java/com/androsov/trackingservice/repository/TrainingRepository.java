package com.androsov.trackingservice.repository;

import com.androsov.trackingservice.entity.Training;
import com.androsov.trackingservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    boolean existsById(Long id);
    List<Training> getAllByUser(User user);
}

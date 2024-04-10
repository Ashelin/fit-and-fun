package com.faf.service;

import com.faf.repositroy.WorkoutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DeleteWorkoutService {

    private final WorkoutRepository workoutRepository;

    public ResponseEntity<Void> deleteWorkout(Long id) {

        workoutRepository.deleteById(id);

        log.info("Workout {} deleted successfully", id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
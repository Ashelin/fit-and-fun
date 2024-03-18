package com.faf.service;

import com.faf.dto.WorkoutRequest;
import com.faf.model.Workout;
import com.faf.repositroy.WorkoutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CreateWorkoutService {

    private final WorkoutRepository workoutRepository;

    public ResponseEntity<Void> createWorkout(WorkoutRequest workoutRequest) {

        Workout workout = Workout.builder()
                .name(workoutRequest.getName())
                .description(workoutRequest.getDescription())
                .build();

        workoutRepository.save(workout);

        log.info("Workout {} saved successfully", workout.getId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

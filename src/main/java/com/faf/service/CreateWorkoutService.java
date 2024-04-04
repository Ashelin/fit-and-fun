package com.faf.service;

import com.faf.dto.WorkoutRequest;
import com.faf.model.Workout;
import com.faf.repositroy.WorkoutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class CreateWorkoutService implements JavaDelegate {

    private final WorkoutRepository workoutRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        WorkoutRequest workoutRequest = WorkoutRequest.builder()
                .name((String) delegateExecution.getVariable("name"))
                .description((String) delegateExecution.getVariable("description"))
                .build();

        Workout workout = Workout.builder()
                .name(workoutRequest.getName())
                .description(workoutRequest.getDescription())
                .build();

        workoutRepository.save(workout);
        log.info("Workout {} saved successfully", workout.getId());
    }

    public ResponseEntity<Void> createWorkout() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
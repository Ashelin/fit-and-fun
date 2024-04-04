package com.faf.service;

import com.faf.repositroy.WorkoutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DeleteWorkoutService implements JavaDelegate {

    private final WorkoutRepository workoutRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("id");

        workoutRepository.deleteById(id);
        log.info("Workout {} deleted successfully", id);
    }

    public ResponseEntity<Void> deleteWorkout() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
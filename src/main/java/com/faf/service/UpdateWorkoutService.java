package com.faf.service;

import com.faf.exception.WorkoutNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.faf.dto.WorkoutRequest;
import com.faf.model.Workout;
import com.faf.repositroy.WorkoutRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateWorkoutService implements JavaDelegate {

    private final WorkoutRepository workoutRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long id = (Long) delegateExecution.getVariable("id");
        WorkoutRequest workoutRequest = WorkoutRequest.builder()
                .name((String) delegateExecution.getVariable("name"))
                .description((String) delegateExecution.getVariable("description"))
                .build();

        Workout existingWorkout = workoutRepository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout not found with id: " + id));

        assignValueFromRequestToEntityIfNotNull(workoutRequest.getName(), existingWorkout::setName);
        assignValueFromRequestToEntityIfNotNull(workoutRequest.getDescription(), existingWorkout::setDescription);
        log.info("Workout {} updated successfully", id);
    }

    @Transactional
    public ResponseEntity<Void> updateWorkout() {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private <T> void assignValueFromRequestToEntityIfNotNull(T requestValue, Consumer<T> consumer) {
        if (requestValue != null)
            consumer.accept(requestValue);
    }
}
package com.faf.service;

import com.faf.exception.WorkoutNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class UpdateWorkoutService {

    private final WorkoutRepository workoutRepository;

    @Transactional
    public ResponseEntity<Void> updateWorkout(Long id, WorkoutRequest workoutRequest) {

        Workout existingWorkout = workoutRepository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout not found with id: " + id));

        assignValueFromRequestToEntityIfNotNull(workoutRequest.getName(), existingWorkout::setName);
        assignValueFromRequestToEntityIfNotNull(workoutRequest.getDescription(), existingWorkout::setDescription);

        log.info("Workout {} updated successfully", id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private <T> void assignValueFromRequestToEntityIfNotNull(T requestValue, Consumer<T> consumer) {
        if (requestValue != null)
            consumer.accept(requestValue);
    }
}
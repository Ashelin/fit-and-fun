package com.faf.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void updateWorkout(Long id, WorkoutRequest workoutRequest) {

        Workout existingWorkout = workoutRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workout not found with id: " + id));

        assignValueFromRequestToEntityIfNotNull(workoutRequest.getName(), existingWorkout::setName);
        assignValueFromRequestToEntityIfNotNull(workoutRequest.getDescription(), existingWorkout::setDescription);

        log.info("Workout {} updated successfully", id);

    }

    private <T> void assignValueFromRequestToEntityIfNotNull(T requestValue, Consumer<T> consumer) {
        if (requestValue != null)
            consumer.accept(requestValue);
    }
}
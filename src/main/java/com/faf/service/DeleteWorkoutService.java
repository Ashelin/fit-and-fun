package com.faf.service;

import com.faf.repositroy.WorkoutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DeleteWorkoutService {

    private final WorkoutRepository workoutRepository;

    public void deleteWorkout(Long id) {

        workoutRepository.deleteById(id);

        log.info("Workout {} delete successfully", id);

    }
}

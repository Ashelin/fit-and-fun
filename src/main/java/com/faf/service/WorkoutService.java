package com.faf.service;

import com.faf.dto.SearchWorkout;
import com.faf.dto.WorkoutRequest;
import com.faf.dto.WorkoutResponse;
import com.faf.model.Workout;
import com.faf.repositroy.WorkoutRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public List<WorkoutResponse> searchWorkout(SearchWorkout searchWorkout) {
        List<Workout> workouts = workoutRepository.search(
                searchWorkout.getName(),
                searchWorkout.getDescription(),
                searchWorkout.getId());

        return workouts.stream().map(this::mapToWorkoutResponse).toList();
    }

    public void createWorkout(WorkoutRequest workoutRequest){

        Workout workout = Workout.builder()
                .name(workoutRequest.getName())
                .description(workoutRequest.getDescription())
                .build();

        workoutRepository.save(workout);

        log.info("Workout {} saved successfully", workout.getId());

    }

    private WorkoutResponse mapToWorkoutResponse(Workout workout) {
        return WorkoutResponse.builder()
                .id(workout.getId())
                .name(workout.getName())
                .description(workout.getDescription())
                .creationTimestamp(workout.getCreationTimestamp())
                .modificationTimestamp(workout.getModificationTimestamp())
                .build();
    }
}
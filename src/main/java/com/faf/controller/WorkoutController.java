package com.faf.controller;

import com.faf.dto.SearchWorkout;
import com.faf.dto.WorkoutRequest;
import com.faf.dto.WorkoutResponse;
import com.faf.service.CreateWorkoutService;
import com.faf.service.DeleteWorkoutService;
import com.faf.service.SearchWorkoutService;
import com.faf.service.UpdateWorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final CreateWorkoutService createWorkoutService;
    private final SearchWorkoutService searchWorkoutService;
    private final DeleteWorkoutService deleteWorkoutService;
    private final UpdateWorkoutService updateWorkoutService;

    @GetMapping(value = "/search")
    public ResponseEntity<Set<WorkoutResponse>> searchWorkouts(@ModelAttribute SearchWorkout searchWorkout) {
        return searchWorkoutService.searchWorkout(searchWorkout);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createWorkout(@RequestBody WorkoutRequest workoutRequest) {
        return createWorkoutService.createWorkout(workoutRequest);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Void> updateWorkout(@PathVariable Long id, @RequestBody WorkoutRequest workoutRequest) {
        return updateWorkoutService.updateWorkout(id, workoutRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        return deleteWorkoutService.deleteWorkout(id);
    }
}
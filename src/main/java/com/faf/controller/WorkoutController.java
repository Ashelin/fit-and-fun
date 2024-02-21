package com.faf.controller;

import com.faf.dto.SearchWorkout;
import com.faf.dto.WorkoutRequest;
import com.faf.dto.WorkoutResponse;
import com.faf.service.CreateWorkoutService;
import com.faf.service.DeleteWorkoutService;
import com.faf.service.SearchWorkoutService;
import com.faf.service.UpdateWorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final CreateWorkoutService createWorkoutService;
    private final SearchWorkoutService searchWorkoutService;
    private final DeleteWorkoutService deleteWorkoutService;
    private final UpdateWorkoutService updateWorkoutService;

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutResponse> searchWorkouts(@ModelAttribute SearchWorkout searchWorkout) {
        return searchWorkoutService.searchWorkout(searchWorkout);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createWorkout(@RequestBody WorkoutRequest workoutRequest) {
        createWorkoutService.createWorkout(workoutRequest);
    }

    @PostMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void createWorkout(@PathVariable Long id, @ModelAttribute WorkoutRequest workoutRequest) {
        updateWorkoutService.updateWorkout(id, workoutRequest);
    }

    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorkout(@PathVariable Long id) {
        deleteWorkoutService.deleteWorkout(id);
    }
}
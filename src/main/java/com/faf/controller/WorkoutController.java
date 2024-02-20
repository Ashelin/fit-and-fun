package com.faf.controller;

import com.faf.dto.WorkoutRequest;
import com.faf.dto.WorkoutResponse;
import com.faf.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/workout")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<WorkoutResponse> getProducts(){
        return workoutService.getWorkouts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWorkout(@RequestBody WorkoutRequest workoutRequest){
        workoutService.createWorkout(workoutRequest);
    }

}
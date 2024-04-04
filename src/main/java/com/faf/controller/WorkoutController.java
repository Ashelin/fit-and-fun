package com.faf.controller;

import com.faf.dto.SearchWorkout;
import com.faf.dto.WorkoutRequest;
import com.faf.dto.WorkoutResponse;
import com.faf.service.CreateWorkoutService;
import com.faf.service.DeleteWorkoutService;
import com.faf.service.SearchWorkoutService;
import com.faf.service.UpdateWorkoutService;
import com.faf.service.client.ViewPageService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/workout")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WorkoutController {

    private final CreateWorkoutService createWorkoutService;
    private final SearchWorkoutService searchWorkoutService;
    private final DeleteWorkoutService deleteWorkoutService;
    private final UpdateWorkoutService updateWorkoutService;
    private final RuntimeService camundaService;
    private final ViewPageService viewPageService;

    @GetMapping(value = "/search")
    public ResponseEntity<List<WorkoutResponse>> searchWorkouts(@ModelAttribute SearchWorkout searchWorkout) {
        camundaService.startProcessInstanceByKey("CRUD_general_process");
        return searchWorkoutService.searchWorkout(searchWorkout);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createWorkout(@RequestBody WorkoutRequest workoutRequest) {
        Long createEventId = 1L;
        viewPageService.setProcessParametersWithPost(createEventId, workoutRequest);
        return createWorkoutService.createWorkout();
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<Void> updateWorkout(@PathVariable Long id, @ModelAttribute WorkoutRequest workoutRequest) {
        Long updateEventId = 2L;
        viewPageService.setProcessParametersWithUpdate(updateEventId, id, workoutRequest);
        return updateWorkoutService.updateWorkout();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        Long deleteEventId = 3L;
        viewPageService.setProcessParametersWithDelete(deleteEventId, id);
        return deleteWorkoutService.deleteWorkout();
    }
}
package com.faf.controller;

import com.faf.dto.SearchWorkout;
import com.faf.dto.WorkoutRequest;
import com.faf.model.Workout;
import com.faf.service.CreateWorkoutService;
import com.faf.service.DeleteWorkoutService;
import com.faf.service.SearchWorkoutService;
import com.faf.service.UpdateWorkoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkoutController.class)
public class WorkoutControllerTest {

    @MockBean
    private CreateWorkoutService createWorkoutService;

    @MockBean
    private SearchWorkoutService searchWorkoutService;

    @MockBean
    private DeleteWorkoutService deleteWorkoutService;

    @MockBean
    private UpdateWorkoutService updateWorkoutService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createWorkout() throws Exception {
        WorkoutRequest workout = new WorkoutRequest("test name", "test description");
        String workoutJson = objectMapper.writeValueAsString(workout);
        mockMvc.perform(post("/api/workout/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(workoutJson))
                .andExpect(status().isOk());
        verify(createWorkoutService, times(3)).createWorkout(workout);
    }

    @Test
    void searchWorkout() throws Exception {
        SearchWorkout searchWorkout = new SearchWorkout(1L, "test name", "test description");
        mockMvc.perform(get("/api/workout/search")
                        .param("id", searchWorkout.getId().toString())
                        .param("name", searchWorkout.getName())
                        .param("description", searchWorkout.getDescription())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(searchWorkoutService, times(1)).searchWorkout(searchWorkout);
    }

    @Test
    void updateWorkout() throws Exception {
        Long workoutId = 1L;
        WorkoutRequest updatedWorkoutRequest = new WorkoutRequest("Updated Workout", "Updated Description");
        String updatedWorkoutJson = objectMapper.writeValueAsString(updatedWorkoutRequest);

        mockMvc.perform(put("/api/workout/update/{id}", workoutId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedWorkoutJson))
                .andExpect(status().isOk());
        verify(updateWorkoutService, times(1)).updateWorkout(eq(workoutId), eq(updatedWorkoutRequest));
    }

    @Test
    void deleteWorkout() throws Exception {
        Workout workoutToDelete = Workout.builder().name("Workout to delete").description("description to delete").build();
        workoutToDelete.setId(1L);
        mockMvc.perform(delete("/api/workout/delete/{id}", workoutToDelete.getId()))
                .andExpect(status().isOk());

        verify(deleteWorkoutService, times(1)).deleteWorkout(eq(workoutToDelete.getId()));
    }
}
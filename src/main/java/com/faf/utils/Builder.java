package com.faf.utils;

import com.faf.dto.WorkoutRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Builder {

    private final HashMap<String, Object> processParams = new HashMap<>();

    public Map<String, Object> buildProcessParamsWithCreate(Long actionId, WorkoutRequest workoutRequest) {
        processParams.put("name", workoutRequest.getName());
        processParams.put("description", workoutRequest.getDescription());
        processParams.put("eventId", actionId);
        return processParams;
    }

    public Map<String, Object> buildProcessParamsWithDelete(Long actionId, Long id) {
        processParams.put("id", id);
        processParams.put("eventId", actionId);
        return processParams;
    }

    public Map<String, Object> buildProcessParamsWithUpdate(Long actionId, Long id, WorkoutRequest workoutRequest) {
        processParams.put("name", workoutRequest.getName());
        processParams.put("description", workoutRequest.getDescription());
        processParams.put("eventId", actionId);
        return processParams;
    }
}
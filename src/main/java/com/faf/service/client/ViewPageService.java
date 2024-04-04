package com.faf.service.client;

import com.faf.dto.WorkoutRequest;
import com.faf.utils.Builder;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ViewPageService {

    private TaskService taskService;

    private final Builder builder;

    public void setProcessParametersWithPost(Long createEventId, WorkoutRequest workoutRequest) {
        Task task = taskService.createTaskQuery()
                .taskDefinitionKey("View_0g1q1ka")
                .singleResult();
            taskService.setVariables(task.getId(), builder.buildProcessParamsWithCreate(createEventId, workoutRequest));
            taskService.complete(task.getId());
    }

    public void setProcessParametersWithDelete(Long createEventId, Long id) {
        Task task = taskService.createTaskQuery()
                .taskDefinitionKey("View_0g1q1ka")
                .singleResult();

            taskService.setVariables(task.getId(), builder.buildProcessParamsWithDelete(createEventId, id));
            taskService.complete(task.getId());
    }

    public void setProcessParametersWithUpdate(Long createEventId, Long id, WorkoutRequest workoutRequest) {
        Task task = taskService.createTaskQuery()
                .taskDefinitionKey("View_0g1q1ka")
                .singleResult();

            taskService.setVariables(task.getId(), builder.buildProcessParamsWithUpdate(createEventId, id, workoutRequest));
            taskService.complete(task.getId());
    }
}
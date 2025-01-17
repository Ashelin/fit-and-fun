package com.faf.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime creationTimestamp;
    private LocalDateTime modificationTimestamp;

}
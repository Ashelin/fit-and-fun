package com.faf.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "WORKOUT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CREATION_TIMESTAMP", updatable = false)
    private LocalDateTime creationTimestamp;
    @Column(name = "MODIFICATION_TIMESTAMP")
    private LocalDateTime modificationTimestamp;

    @PrePersist
    protected void onCreate() {
        creationTimestamp = LocalDateTime.now();
        modificationTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modificationTimestamp = LocalDateTime.now();
    }

}
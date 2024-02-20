package com.faf.repositroy;

import com.faf.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT w FROM Workout w WHERE " +
            "(:name IS NULL OR w.name LIKE %:name%) AND " +
            "(:description IS NULL OR w.description = :description) AND " +
            "(:id IS NULL OR w.id = :id) OR " +
            "(:name IS NULL AND :description IS NULL AND :id IS NULL)")
    List<Workout> search(String name, String description, Long id);

}
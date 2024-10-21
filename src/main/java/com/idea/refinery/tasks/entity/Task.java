package com.idea.refinery.tasks.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDate createDate;
    private LocalDate completedDate;
}

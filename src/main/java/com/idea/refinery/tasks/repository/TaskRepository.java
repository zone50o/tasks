package com.idea.refinery.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idea.refinery.tasks.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    Task findByTaskId(Long taskId);
}

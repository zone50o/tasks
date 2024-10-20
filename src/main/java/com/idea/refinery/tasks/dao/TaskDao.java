package com.idea.refinery.tasks.dao;

import com.idea.refinery.tasks.entity.Task;
import com.idea.refinery.tasks.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskDao {

    final private TaskRepository taskRepository;

    public TaskDao(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findByTaskId(Long taskId) {
        return taskRepository.findByTaskId(taskId);
    }

}

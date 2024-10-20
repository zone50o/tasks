package com.idea.refinery.tasks.services;

import java.util.List;
import java.util.Objects;

import com.idea.refinery.tasks.dao.TaskDao;

import com.idea.refinery.tasks.entity.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    final private TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public Task saveTask(Task task) {
        return taskDao.save(task);
    }

    public List<Task> fetchTaskList() {
       return taskDao.findAll();
    }


    public Task fetchTaskById(Long taskId) {
        return taskDao.findByTaskId(taskId);
    }

    public Task updateTask(Task task, Long taskId) {
        Task fetchedTask = taskDao.findByTaskId(taskId);
        if(fetchedTask!=null){
            if(Objects.nonNull(task.getTitle())&&!"".equalsIgnoreCase(task.getTitle())){
                fetchedTask.setTitle(task.getTitle());
            }
            if(Objects.nonNull(task.getDescription())&&!"".equalsIgnoreCase(task.getDescription())){
                fetchedTask.setDescription(task.getDescription());
            }
            if(Objects.nonNull(task.getCompleted())){
                fetchedTask.setCompleted(task.getCompleted());
            }
            if(Objects.nonNull(task.getCompletedDate())){
                fetchedTask.setCompletedDate(task.getCompletedDate());
            }
            return taskDao.save(fetchedTask);
        }
        
        return null;
    }

}

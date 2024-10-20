package com.idea.refinery.tasks.api;

import org.springframework.web.bind.annotation.RestController;

import com.idea.refinery.tasks.services.TaskService;
import com.idea.refinery.tasks.entity.Task;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class TaskApiImpl implements TaskApi{

    final private TaskService taskService;

    public TaskApiImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> list = taskService.fetchTaskList();
  
        if(list.size() <= 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(list,HttpStatus.OK);
        }
    }

    @Override
	public ResponseEntity<Task> getTaskById(Long id) {
        Task fetchedTask = taskService.fetchTaskById(id);
        if(fetchedTask!=null){
            return new ResponseEntity<>(fetchedTask, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
	public ResponseEntity<Task> saveTask(Task newTask) {
        return new ResponseEntity<>(taskService.saveTask(newTask), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Task> updateTask(Long id, Task task) {
        Task updatedTask = taskService.updateTask(task, id);
        if(updatedTask!=null){
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
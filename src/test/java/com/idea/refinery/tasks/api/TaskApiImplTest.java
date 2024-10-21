package com.idea.refinery.tasks.api;

import com.idea.refinery.tasks.entity.Task;
import com.idea.refinery.tasks.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class TaskApiImplTest {

    TaskApiImpl taskApi;

    TaskService taskService = Mockito.mock(TaskService.class);

    @BeforeEach
    void setUp() {
        taskApi = new TaskApiImpl(taskService);
    }

    @Test
    void When_saveTask_AND_Task_Expect_OKResponse() {
        Task task = new Task();
        Mockito.when(taskService.saveTask(any())).thenReturn(task);
        ResponseEntity<Task> returnedResponse =new ResponseEntity<>(task, HttpStatus.OK);
        assertEquals(returnedResponse,taskApi.saveTask(task));
    }

    @Test
    void When_getTaskByID_AND_TaskInDB_Expect_OKResponse() {
        Task task = new Task();
        Mockito.when(taskService.fetchTaskById(any())).thenReturn(task);
        ResponseEntity<Task> returnedResponse =new ResponseEntity<>(task, HttpStatus.OK);
        assertEquals(returnedResponse,taskApi.getTaskById(1L));
    }

    @Test
    void When_getTaskByID_AND_NoTaskInDB_Expect_NotFoundResponse() {
        Mockito.when(taskService.fetchTaskById(any())).thenReturn(null);
        ResponseEntity<Task> returnedResponse =new ResponseEntity<>(HttpStatus.NOT_FOUND);
        assertEquals(returnedResponse,taskApi.getTaskById(1L));
    }

    @Test
    void When_fetchTaskList_AND_TasksInDB_Expect_OKResponse() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Mockito.when(taskService.fetchTaskList()).thenReturn(tasks);
        ResponseEntity<List<Task>> returnedResponse =new ResponseEntity<>(tasks, HttpStatus.OK);
        assertEquals(returnedResponse,taskApi.getAllTasks());
    }

    @Test
    void When_fetchTaskList_AND_NoTasksInDB_Expect_NotFoundResponse() {
        List<Task> tasks = new ArrayList<>();
        Mockito.when(taskService.fetchTaskList()).thenReturn(tasks);
        ResponseEntity<List<Task>> returnedResponse =new ResponseEntity<>(HttpStatus.NOT_FOUND);
        assertEquals(returnedResponse,taskApi.getAllTasks());
    }

    @Test
    void When_updateTask_AND_UpdatedTaskPopulated_Expect_OKResponse() {
        Task task = new Task(1L,"task1","description",true,
                LocalDate.of(2024,10,20),
                LocalDate.of(2024,10,20));
        Mockito.when(taskService.updateTask(any(),any())).thenReturn(task);
        ResponseEntity<Task> returnedResponse =new ResponseEntity<>(task, HttpStatus.OK);
        assertEquals(returnedResponse,taskApi.updateTask(1L,task));

    }

    @Test
    void When_updateTask_AND_UpdatedTaskNull_Expect_NotFoundResponse() {
        Mockito.when(taskService.updateTask(any(),any())).thenReturn(null);
        ResponseEntity<Task> returnedResponse =new ResponseEntity<>(HttpStatus.NOT_FOUND);
        assertEquals(returnedResponse,taskApi.updateTask(1L,new Task()));
    }
}
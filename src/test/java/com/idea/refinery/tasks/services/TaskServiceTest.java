package com.idea.refinery.tasks.services;

import com.idea.refinery.tasks.dao.TaskDao;
import com.idea.refinery.tasks.entity.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

class TaskServiceTest {

    TaskService taskService;

    TaskDao taskDao = Mockito.mock(TaskDao.class);

    @BeforeEach
    void setUp() {
        taskService = new TaskService(taskDao);
    }

    @Test
    void When_saveTask_AND_Task_Expect_Task() {
        Task task = new Task();
        Mockito.when(taskDao.save(any())).thenReturn(task);
        assertEquals(task,taskService.saveTask(task));
    }

    @Test
    void When_fetchTaskById_AND_TaskInDB_Expect_fetchedTasksToBePopulated() {
        Task task = new Task();
        Mockito.when(taskDao.findByTaskId(any())).thenReturn(task);
        assertEquals(task,taskService.fetchTaskById(1L));
    }

    @Test
    void When_fetchTaskById_AND_NoTaskInDB_Expect_fetchedTaskToBeNull() {
        Mockito.when(taskDao.findByTaskId(any())).thenReturn(null);
        assertNull(taskService.fetchTaskById(1L));
    }

    @Test
    void When_fetchTaskList_AND_TasksInDB_Expect_fetchedTasksToBePopulated() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Mockito.when(taskDao.findAll()).thenReturn(tasks);
        assertFalse(taskService.fetchTaskList().isEmpty());
    }

    @Test
    void When_fetchTaskList_AND_NoTasksInDB_Expect_fetchedTasksToEmpty() {
        List<Task> tasks = new ArrayList<>();
        Mockito.when(taskDao.findAll()).thenReturn(tasks);
        assertTrue(taskService.fetchTaskList().isEmpty());
    }

    @Test
    void When_updateTask_AND_ParamNotProvided_Expect_NoChange() {
        Task task = new Task(1L,"task1","description",true,
                                LocalDate.of(2024,10,20),
                                LocalDate.of(2024,10,20));
        Task taskUpdate = new Task(null,null,null,null,null,null);
        Mockito.when(taskDao.findByTaskId(any())).thenReturn(task);
        Mockito.when(taskDao.save(any())).then(returnsFirstArg());
        Task updatedTask = taskService.updateTask(taskUpdate,1L);

        assertEquals(task.getTaskId(),updatedTask.getTaskId());
        assertEquals(task.getTitle(),updatedTask.getTitle());
        assertEquals(task.getDescription(),updatedTask.getDescription());
        assertEquals(task.getCompleted(),updatedTask.getCompleted());
        assertEquals(task.getCreateDate(),updatedTask.getCreateDate());
        assertEquals(task.getCompletedDate(),updatedTask.getCompletedDate());
    }

    @Test
    void When_updateTask_AND_EmptyStringParam_Expect_NoChange() {
        Task task = new Task(1L,"task1","description",true,
                LocalDate.of(2024,10,20),
                LocalDate.of(2024,10,20));
        Task taskUpdate = new Task(null,"","",null,null,null);
        Mockito.when(taskDao.findByTaskId(any())).thenReturn(task);
        Mockito.when(taskDao.save(any())).then(returnsFirstArg());
        Task updatedTask = taskService.updateTask(taskUpdate,1L);

        assertEquals(task.getTaskId(),updatedTask.getTaskId());
        assertEquals(task.getTitle(),updatedTask.getTitle());
        assertEquals(task.getDescription(),updatedTask.getDescription());
        assertEquals(task.getCompleted(),updatedTask.getCompleted());
        assertEquals(task.getCreateDate(),updatedTask.getCreateDate());
        assertEquals(task.getCompletedDate(),updatedTask.getCompletedDate());
    }

    @Test
    void When_updateTask_AND_ParamProvided_Expect_Change() {
        Task task = new Task(1L,"task1","description",true,
                LocalDate.of(2024,10,20),
                LocalDate.of(2024,10,20));
        Task taskUpdate = new Task(2L,"task2","description2",false,
                LocalDate.of(2025,11,21),
                LocalDate.of(2025,11,21));
        Mockito.when(taskDao.findByTaskId(any())).thenReturn(task);
        Mockito.when(taskDao.save(any())).then(returnsFirstArg());
        Task updatedTask = taskService.updateTask(taskUpdate,1L);

        assertEquals(task.getTaskId(),updatedTask.getTaskId());
        assertEquals(taskUpdate.getTitle(),updatedTask.getTitle());
        assertEquals(taskUpdate.getDescription(),updatedTask.getDescription());
        assertEquals(taskUpdate.getCompleted(),updatedTask.getCompleted());
        //Design choice that createDate should be immutable
        assertEquals(task.getCreateDate(),updatedTask.getCreateDate());
        assertEquals(taskUpdate.getCompletedDate(),updatedTask.getCompletedDate());
    }

    @Test
    void When_updateTask_AND_NoMatchingTask_Expect_Null() {
        Task taskUpdate = new Task(2L,"task2","description2",false,
                LocalDate.of(2025,11,21),
                LocalDate.of(2025,11,21));
        Mockito.when(taskDao.findByTaskId(any())).thenReturn(null);
        Task updatedTask = taskService.updateTask(taskUpdate,1L);

        assertNull(updatedTask);
    }
}
package com.idea.refinery.tasks.dao;

import com.idea.refinery.tasks.entity.Task;
import com.idea.refinery.tasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;

class TaskDaoTest {

    TaskDao taskDao;

    TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

    @BeforeEach
    void setUp() {
        taskDao = new TaskDao(taskRepository);
    }
    @Test
    void when_save_And_Task_Expect_task() {
        Task task = new Task();
        Mockito.when(taskRepository.save(any())).thenReturn(task);
        assertEquals(task,taskDao.save(task));
    }

    @Test
    void when_findAll_AND_TaskInDB_Expect_TasksToBePopulated() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);
        assertFalse(taskDao.findAll().isEmpty());
    }

    @Test
    void when_findAll_AND_TaskInDB_Expect_TasksToBeEmpty() {
        List<Task> tasks = new ArrayList<>();
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);
        assertTrue(taskDao.findAll().isEmpty());
    }

    @Test
    void When_fetchTaskById_AND_TaskInDB_Expect_fetchedTasksToBePopulated() {
        Task task = new Task();
        Mockito.when(taskRepository.findByTaskId(any())).thenReturn(task);
        assertEquals(task,taskDao.findByTaskId(1L));
    }

    @Test
    void When_fetchTaskById_AND_NoTaskInDB_Expect_fetchedTaskToBeNull() {
        Mockito.when(taskRepository.findByTaskId(any())).thenReturn(null);
        assertNull(taskDao.findByTaskId(1L));
    }
}
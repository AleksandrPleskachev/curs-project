package com.skfu.project.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TaskMediatorTest {

    @Autowired
    private TaskMediator taskMediator;

    @Test
    void testContextLoads() {
        assertNotNull(taskMediator);
    }

    @Test
    void testCreateTask() throws Exception {
        var method = TaskMediator.class.getMethod("createTask", com.skfu.project.entity.Task.class);
        assertNotNull(method);
    }

    @Test
    void testFindTaskById() throws Exception {
        var method = TaskMediator.class.getMethod("findTaskById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllTasks() throws Exception {
        var method = TaskMediator.class.getMethod("findAllTasks");
        assertNotNull(method);
    }

    @Test
    void testUpdateTask() throws Exception {
        var method = TaskMediator.class.getMethod("updateTask", com.skfu.project.entity.Task.class);
        assertNotNull(method);
    }

    @Test
    void testDeleteTaskById() throws Exception {
        var method = TaskMediator.class.getMethod("deleteTaskById", Long.class);
        assertNotNull(method);
    }
}

package com.skfu.project.control.impl;

import com.skfu.project.control.TaskControl;
import com.skfu.project.entity.Task;
import com.skfu.project.mediator.TaskMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskControlImpl implements TaskControl {

    @Autowired
    private TaskMediator taskMediator;

    @Override
    public Task createTask(Task task) {
        return taskMediator.createTask(task);
    }

    @Override
    public Task findTaskById(Long id) {
        return taskMediator.findTaskById(id);
    }

    @Override
    public List<Task> findTasksByProjectId(Long projectId) {
        return taskMediator.findTasksByProjectId(projectId);
    }

    @Override
    public Task updateTaskStatus(Long taskId, String status) {
        taskMediator.updateTaskStatus(taskId, status);
        return taskMediator.findTaskById(taskId);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskMediator.findAllTasks();
    }

    @Override
    public Task updateTask(Task task) {
        return taskMediator.updateTask(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskMediator.deleteTaskById(id);
    }

    @Override
    public java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm() {
        return taskMediator.findAllProjectsForForm();
    }

    @Override
    public java.util.List<com.skfu.project.entity.Employee> findAllEmployeesForForm() {
        return taskMediator.findAllEmployeesForForm();
    }
}

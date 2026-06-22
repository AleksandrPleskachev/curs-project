package com.skfu.project.mediator;

import com.skfu.project.entity.Task;
import com.skfu.project.foundation.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskMediator {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskMediator(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Конструктор для тестов
    public TaskMediator() {
        this(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public Task findTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<Task> findTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Task updateTask(Task task) {
        if (!taskRepository.existsById(task.getId())) {
            throw new RuntimeException("Task not found with id " + task.getId());
        }
        return taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteTaskById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void updateTaskStatus(Long id, String status) {
        Task task = findTaskById(id);
        task.setStatus(status);
    }

    @Transactional(readOnly = true)
    public java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm() {
        return taskRepository.findAllProjects();
    }

    @Transactional(readOnly = true)
    public java.util.List<com.skfu.project.entity.Employee> findAllEmployeesForForm() {
        return taskRepository.findAllEmployees();
    }
}

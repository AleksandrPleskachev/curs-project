package com.skfu.project.mediator;

import com.skfu.project.entity.Project;
import com.skfu.project.foundation.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectMediator {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectMediator(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Конструктор для тестов
    public ProjectMediator() {
        this(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Project createProject(Project project) {
        // Простая валидация
        if (project.getBudget().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Budget must be greater than zero");
        }
        if (project.getStartDate().isAfter(project.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        return projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public Project findProjectById(Long id) {
        return projectRepository.findByIdWithRelations(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<Project> findAllProjects() {
        return projectRepository.findAllWithRelations();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Project updateProject(Project project) {
        if (!projectRepository.existsById(project.getId())) {
            throw new RuntimeException("Project not found with id " + project.getId());
        }
        return projectRepository.save(project);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found with id " + id);
        }
        projectRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<com.skfu.project.entity.Customer> findAllCustomersForProjectForm() {
        return projectRepository.findAllCustomers();
    }
}

package com.skfu.project.control.impl;

import com.skfu.project.control.ProjectControl;
import com.skfu.project.entity.Project;
import com.skfu.project.mediator.ProjectMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectControlImpl implements ProjectControl {

    @Autowired
    private ProjectMediator projectMediator;

    @Override
    public Project createProject(Project project) {
        return projectMediator.createProject(project);
    }

    @Override
    public Project findProjectById(Long id) {
        return projectMediator.findProjectById(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectMediator.findAllProjects();
    }

    @Override
    public Project updateProject(Project project) {
        return projectMediator.updateProject(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectMediator.deleteProjectById(id);
    }

    @Override
    public java.util.List<com.skfu.project.entity.Customer> findAllCustomersForProjectForm() {
        return projectMediator.findAllCustomersForProjectForm();
    }
}
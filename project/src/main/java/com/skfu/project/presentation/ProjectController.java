package com.skfu.project.presentation;

import com.skfu.project.control.ProjectControl;
import com.skfu.project.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectControl projectControl;

    // Список всех проектов (доступно всем аутентифицированным)
    @GetMapping
    public String listProjects(Model model) {
        List<Project> projects = projectControl.findAllProjects();
        model.addAttribute("projects", projects);
        return "projects/list";
    }

    // Детали проекта (доступно всем аутентифицированным)
    @GetMapping("/{id}")
    public String showProject(@PathVariable Long id, Model model) {
        Project project = projectControl.findProjectById(id);
        model.addAttribute("project", project);
        return "projects/detail";
    }

    // Форма создания проекта (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createProjectForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("customers", projectControl.findAllCustomersForProjectForm());
        return "projects/form";
    }

    // Создание проекта (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createProject(@ModelAttribute Project project) {
        projectControl.createProject(project);
        return "redirect:/projects";
    }

    // Форма редактирования проекта (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editProjectForm(@PathVariable Long id, Model model) {
        Project project = projectControl.findProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("customers", projectControl.findAllCustomersForProjectForm());
        return "projects/form";
    }

    // Обновление проекта (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateProject(@PathVariable Long id, @ModelAttribute Project project) {
        project.setId(id);
        projectControl.updateProject(project);
        return "redirect:/projects/{id}";
    }

    // Удаление проекта (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectControl.deleteProjectById(id);
        return "redirect:/projects";
    }
}
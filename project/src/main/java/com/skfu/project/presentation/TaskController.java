package com.skfu.project.presentation;

import com.skfu.project.control.TaskControl;
import com.skfu.project.entity.Task;
import com.skfu.project.mediator.UserMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskControl taskControl;

    @Autowired
    private UserMediator userMediator;

    // Список задач (ADMIN - все, USER - только свои)
    @GetMapping
    public String listTasks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        com.skfu.project.entity.User user = userMediator.findByUsername(username);
        model.addAttribute("isAdmin", user.getRole().name().equals("ADMIN"));
        
        if (user.getRole().name().equals("ADMIN")) {
            // Админ видит все задачи
            List<Task> tasks = taskControl.findAllTasks();
            model.addAttribute("tasks", tasks);
        } else {
            // Сотрудник видит только свои задачи
            List<Task> tasks = userMediator.findTasksByCurrentUser();
            model.addAttribute("tasks", tasks);
        }
        return "tasks/list";
    }

    // Мои задачи (User)
    @GetMapping("/my")
    public String listMyTasks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        com.skfu.project.entity.User user = userMediator.findByUsername(username);
        model.addAttribute("isAdmin", false);
        
        List<Task> tasks = userMediator.findTasksByCurrentUser();
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    // Детали задачи (ADMIN - любая, USER - только своя)
    @GetMapping("/{id}")
    public String showTask(@PathVariable Long id, Model model) {
        Task task = taskControl.findTaskById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        com.skfu.project.entity.User user = userMediator.findByUsername(username);
        
        // Проверяем, является ли задача пользователевой
        if (user.getRole().name().equals("USER") && task.getEmployee() != null) {
            com.skfu.project.entity.Employee employee = userMediator.getCurrentEmployee();
            if (task.getEmployee().getId().equals(employee.getId())) {
                model.addAttribute("task", task);
                return "tasks/detail";
            } else {
                return "redirect:/tasks";
            }
        }
        model.addAttribute("task", task);
        return "tasks/detail";
    }

    // Форма создания задачи (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projects", taskControl.findAllProjectsForForm());
        model.addAttribute("employees", taskControl.findAllEmployeesForForm());
        return "tasks/form";
    }

    // Создание задачи (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskControl.createTask(task);
        return "redirect:/tasks";
    }

    // Форма редактирования задачи (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskControl.findTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("projects", taskControl.findAllProjectsForForm());
        model.addAttribute("employees", taskControl.findAllEmployeesForForm());
        return "tasks/form";
    }

    // Обновление задачи (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);
        taskControl.updateTask(task);
        return "redirect:/tasks/{id}";
    }

    // Обновление статуса задачи (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/status")
    public String updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        taskControl.updateTaskStatus(id, status);
        return "redirect:/tasks";
    }

    // Удаление задачи (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskControl.deleteTaskById(id);
        return "redirect:/tasks";
    }

    // Задачи по проекту (доступно всем аутентифицированным)
    @GetMapping("/project/{projectId}")
    public String listTasksByProject(@PathVariable Long projectId, Model model) {
        List<Task> tasks = taskControl.findTasksByProjectId(projectId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("projectId", projectId);
        return "tasks/list_by_project";
    }
}

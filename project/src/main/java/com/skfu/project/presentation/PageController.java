package com.skfu.project.presentation;

import com.skfu.project.mediator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ProjectMediator projectMediator;

    @Autowired
    private TaskMediator taskMediator;

    @Autowired
    private EstimateMediator estimateMediator;

    @Autowired
    private EmployeeMediator employeeMediator;

    @Autowired
    private ContractMediator contractMediator;

    @Autowired
    private UserMediator userMediator;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        
        // Получаем информацию о роли пользователя
        com.skfu.project.entity.User currentUser = userMediator.findByUsername(username);
        model.addAttribute("isAdmin", currentUser.getRole().name().equals("ADMIN"));
        model.addAttribute("isUser", currentUser.getRole().name().equals("USER"));
        
        return "dashboard";
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("projects", projectMediator.findAllProjects());
        model.addAttribute("tasks", taskMediator.findAllTasks());
        model.addAttribute("estimates", estimateMediator.findAllEstimates());
        model.addAttribute("employees", employeeMediator.findAllEmployees());
        model.addAttribute("contracts", contractMediator.findAllContracts());
        
        // Рассчитаем статистику
        long activeProjectsCount = projectMediator.findAllProjects().stream()
            .filter(p -> "ACTIVE".equals(p.getStatus())).count();
        long completedProjectsCount = projectMediator.findAllProjects().stream()
            .filter(p -> "COMPLETED".equals(p.getStatus())).count();
        
        BigDecimal totalBudget = projectMediator.findAllProjects().stream()
            .map(p -> p.getBudget()).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        long inProgressTasksCount = taskMediator.findAllTasks().stream()
            .filter(t -> "IN_PROGRESS".equals(t.getStatus())).count();
        long doneTasksCount = taskMediator.findAllTasks().stream()
            .filter(t -> "DONE".equals(t.getStatus())).count();
        long todoTasksCount = taskMediator.findAllTasks().stream()
            .filter(t -> "TODO".equals(t.getStatus())).count();
        
        long approvedEstimatesCount = estimateMediator.findAllEstimates().stream()
            .filter(e -> "APPROVED".equals(e.getStatus())).count();
        BigDecimal totalEstimateCost = estimateMediator.findAllEstimates().stream()
            .map(e -> e.getTotalCost()).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        long employedEmployeesCount = taskMediator.findAllTasks().stream()
            .filter(t -> t.getEmployee() != null).distinct().count();
        
        long activeContractsCount = contractMediator.findAllContracts().stream()
            .filter(c -> "ACTIVE".equals(c.getStatus())).count();
        BigDecimal totalContractAmount = contractMediator.findAllContracts().stream()
            .map(c -> c.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        
        model.addAttribute("activeProjectsCount", activeProjectsCount);
        model.addAttribute("completedProjectsCount", completedProjectsCount);
        model.addAttribute("totalBudget", totalBudget);
        model.addAttribute("inProgressTasksCount", inProgressTasksCount);
        model.addAttribute("doneTasksCount", doneTasksCount);
        model.addAttribute("todoTasksCount", todoTasksCount);
        model.addAttribute("approvedEstimatesCount", approvedEstimatesCount);
        model.addAttribute("totalEstimateCost", totalEstimateCost);
        model.addAttribute("employedEmployeesCount", employedEmployeesCount);
        model.addAttribute("activeContractsCount", activeContractsCount);
        model.addAttribute("totalContractAmount", totalContractAmount);
        
        return "reports";
    }
}

package com.skfu.project.presentation;

import com.skfu.project.control.EmployeeControl;
import com.skfu.project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeControl employeeControl;

    // Список всех сотрудников (доступно всем аутентифицированным)
    @GetMapping
    public String listEmployees(Model model) {
        List<Employee> employees = employeeControl.findAllEmployees();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    // Детали сотрудника (доступно всем аутентифицированным)
    @GetMapping("/{id}")
    public String showEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeControl.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/detail";
    }

    // Форма создания сотрудника (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/form";
    }

    // Создание сотрудника (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createEmployee(@ModelAttribute Employee employee) {
        employeeControl.createEmployee(employee);
        return "redirect:/employees";
    }

    // Форма редактирования сотрудника (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeControl.findEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employees/form";
    }

    // Обновление сотрудника (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        employee.setId(id);
        employeeControl.updateEmployee(employee);
        return "redirect:/employees/{id}";
    }

    // Удаление сотрудника (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        employeeControl.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}

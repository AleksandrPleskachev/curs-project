package com.skfu.project.presentation;

import com.skfu.project.entity.User;
import com.skfu.project.mediator.EmployeeMediator;
import com.skfu.project.mediator.UserMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserAdminController {

    @Autowired
    private UserMediator userMediator;

    @Autowired
    private EmployeeMediator employeeMediator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Список всех пользователей
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userMediator.findAllUsers());
        return "admin/users/list";
    }

    // Форма создания пользователя
    @GetMapping("/new")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", User.Role.values());
        model.addAttribute("employees", employeeMediator.findAllEmployees());
        return "admin/users/form";
    }

    // Создание пользователя
    @PostMapping
    public String createUser(@ModelAttribute User user, @RequestParam(required = false) Long employeeId) {
        // Шифруем пароль
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        // Привязываем сотрудника, если указан
        if (employeeId != null) {
            try {
                var employee = employeeMediator.findEmployeeById(employeeId);
                user.setEmployee(employee);
            } catch (Exception e) {
                // Если сотрудник не найден, продолжаем без привязки
            }
        }
        userMediator.createUser(user);
        return "redirect:/admin/users";
    }

    // Форма редактирования пользователя
    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userMediator.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", User.Role.values());
        model.addAttribute("employees", employeeMediator.findAllEmployees());
        return "admin/users/form";
    }

    // Обновление пользователя
    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String username, @RequestParam String email, @RequestParam String role, @RequestParam(required = false) String password, @RequestParam(required = false) Long employeeId) {
        // Загружаем существующего пользователя
        User user = userMediator.findUserById(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(com.skfu.project.entity.User.Role.valueOf(role));
        
        // Если пароль указан, шифруем его
        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        // Если пароль не указан, оставляем старый
        
        // Обновляем привязку сотрудника, если указан
        if (employeeId != null && employeeId > 0) {
            try {
                var employee = employeeMediator.findEmployeeById(employeeId);
                user.setEmployee(employee);
            } catch (Exception e) {
                // Если сотрудник не найден, продолжаем без привязки
            }
        }
        userMediator.updateUser(user);
        return "redirect:/admin/users";
    }

    // Удаление пользователя
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userMediator.deleteUserById(id);
        return "redirect:/admin/users";
    }

    // Привязка сотрудника к пользователю
    @PostMapping("/{id}/link-employee")
    public String linkEmployee(@PathVariable Long id) {
        userMediator.linkEmployeeToUser(id);
        return "redirect:/admin/users";
    }
}

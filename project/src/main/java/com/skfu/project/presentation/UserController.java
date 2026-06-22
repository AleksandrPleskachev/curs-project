package com.skfu.project.presentation;

import com.skfu.project.mediator.UserMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserMediator userMediator;

    // Мой профиль (доступно всем аутентифицированным)
    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("user", userMediator.findByUsername(username));
        return "profile";
    }

    // Мои задачи (доступно всем аутентифицированным)
    @GetMapping("/my-tasks")
    public String myTasks(Model model) {
        model.addAttribute("tasks", userMediator.findTasksByCurrentUser());
        return "my-tasks";
    }
}

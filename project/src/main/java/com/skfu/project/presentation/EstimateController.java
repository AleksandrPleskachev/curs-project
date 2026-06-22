package com.skfu.project.presentation;

import com.skfu.project.control.EstimateControl;
import com.skfu.project.entity.Estimate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estimates")
public class EstimateController {

    @Autowired
    private EstimateControl estimateControl;

    // Список всех смет (доступно всем аутентифицированным)
    @GetMapping
    public String listEstimates(Model model) {
        List<Estimate> estimates = estimateControl.findAllEstimates();
        model.addAttribute("estimates", estimates);
        return "estimates/list";
    }

    // Детали сметы (доступно всем аутентифицированным)
    @GetMapping("/{id}")
    public String showEstimate(@PathVariable Long id, Model model) {
        Estimate estimate = estimateControl.findEstimateById(id);
        model.addAttribute("estimate", estimate);
        return "estimates/detail";
    }

    // Форма создания сметы (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createEstimateForm(Model model) {
        model.addAttribute("estimate", new Estimate());
        model.addAttribute("projects", estimateControl.findAllProjectsForForm());
        return "estimates/form";
    }

    // Создание сметы (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createEstimate(@ModelAttribute Estimate estimate) {
        estimateControl.createEstimate(estimate);
        return "redirect:/estimates";
    }

    // Форма редактирования сметы (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editEstimateForm(@PathVariable Long id, Model model) {
        Estimate estimate = estimateControl.findEstimateById(id);
        model.addAttribute("estimate", estimate);
        model.addAttribute("projects", estimateControl.findAllProjectsForForm());
        return "estimates/form";
    }

    // Обновление сметы (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateEstimate(@PathVariable Long id, @ModelAttribute Estimate estimate) {
        estimate.setId(id);
        estimateControl.updateEstimate(estimate);
        return "redirect:/estimates/{id}";
    }

    // Удаление сметы (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteEstimate(@PathVariable Long id) {
        estimateControl.deleteEstimateById(id);
        return "redirect:/estimates";
    }
}

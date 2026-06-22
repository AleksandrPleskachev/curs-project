package com.skfu.project.presentation;

import com.skfu.project.control.ContractControl;
import com.skfu.project.entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    private ContractControl contractControl;

    // Список всех договоров (доступно всем аутентифицированным)
    @GetMapping
    public String listContracts(Model model) {
        model.addAttribute("contracts", contractControl.findAllContracts());
        return "contracts/list";
    }

    // Детали договора (доступно всем аутентифицированным)
    @GetMapping("/{id}")
    public String showContract(@PathVariable Long id, Model model) {
        Contract contract = contractControl.findContractById(id);
        model.addAttribute("contract", contract);
        return "contracts/detail";
    }

    // Форма создания договора (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createContractForm(Model model) {
        model.addAttribute("contract", new Contract());
        model.addAttribute("projects", contractControl.findAllProjectsForForm());
        return "contracts/form";
    }

    // Создание договора (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createContract(@ModelAttribute Contract contract) {
        contractControl.createContract(contract);
        return "redirect:/contracts";
    }

    // Форма редактирования договора (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editContractForm(@PathVariable Long id, Model model) {
        Contract contract = contractControl.findContractById(id);
        model.addAttribute("contract", contract);
        model.addAttribute("projects", contractControl.findAllProjectsForForm());
        return "contracts/form";
    }

    // Обновление договора (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateContract(@PathVariable Long id, @ModelAttribute Contract contract) {
        contract.setId(id);
        contractControl.updateContract(contract);
        return "redirect:/contracts/{id}";
    }

    // Удаление договора (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteContract(@PathVariable Long id) {
        contractControl.deleteContractById(id);
        return "redirect:/contracts";
    }
}

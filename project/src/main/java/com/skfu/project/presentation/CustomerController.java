package com.skfu.project.presentation;

import com.skfu.project.control.CustomerControl;
import com.skfu.project.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerControl customerControl;

    // Список всех заказчиков (доступно всем аутентифицированным)
    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customers = customerControl.findAllCustomers();
        model.addAttribute("customers", customers);
        return "customers/list";
    }

    // Детали заказчика (доступно всем аутентифицированным)
    @GetMapping("/{id}")
    public String showCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerControl.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "customers/detail";
    }

    // Форма создания заказчика (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers/form";
    }

    // Создание заказчика (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createCustomer(@ModelAttribute Customer customer) {
        customerControl.createCustomer(customer);
        return "redirect:/customers";
    }

    // Форма редактирования заказчика (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/edit")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerControl.findCustomerById(id);
        model.addAttribute("customer", customer);
        return "customers/form";
    }

    // Обновление заказчика (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer customer) {
        customer.setId(id);
        customerControl.updateCustomer(customer);
        return "redirect:/customers/{id}";
    }

    // Удаление заказчика (только ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {
        customerControl.deleteCustomerById(id);
        return "redirect:/customers";
    }
}

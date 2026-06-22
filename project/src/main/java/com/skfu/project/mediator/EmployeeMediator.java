package com.skfu.project.mediator;

import com.skfu.project.entity.Employee;
import com.skfu.project.foundation.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeMediator {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    @Transactional(readOnly = true)
    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Employee updateEmployee(Employee employee) {
        if (!employeeRepository.existsById(employee.getId())) {
            throw new RuntimeException("Employee not found with id " + employee.getId());
        }
        return employeeRepository.save(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id " + id);
        }
        employeeRepository.deleteById(id);
    }
}

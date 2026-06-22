package com.skfu.project.control.impl;

import com.skfu.project.control.EmployeeControl;
import com.skfu.project.entity.Employee;
import com.skfu.project.mediator.EmployeeMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeControlImpl implements EmployeeControl {

    @Autowired
    private EmployeeMediator employeeMediator;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeMediator.createEmployee(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeMediator.findEmployeeById(id);
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeMediator.findEmployeeByEmail(email);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeMediator.findAllEmployees();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeMediator.updateEmployee(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeMediator.deleteEmployeeById(id);
    }
}
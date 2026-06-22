package com.skfu.project.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeMediatorTest {

    @Autowired
    private EmployeeMediator employeeMediator;

    @Test
    void testContextLoads() {
        assertNotNull(employeeMediator);
    }

    @Test
    void testCreateEmployee() throws Exception {
        var method = EmployeeMediator.class.getMethod("createEmployee", com.skfu.project.entity.Employee.class);
        assertNotNull(method);
    }

    @Test
    void testFindEmployeeById() throws Exception {
        var method = EmployeeMediator.class.getMethod("findEmployeeById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllEmployees() throws Exception {
        var method = EmployeeMediator.class.getMethod("findAllEmployees");
        assertNotNull(method);
    }

    @Test
    void testUpdateEmployee() throws Exception {
        var method = EmployeeMediator.class.getMethod("updateEmployee", com.skfu.project.entity.Employee.class);
        assertNotNull(method);
    }

    @Test
    void testDeleteEmployeeById() throws Exception {
        var method = EmployeeMediator.class.getMethod("deleteEmployeeById", Long.class);
        assertNotNull(method);
    }
}

package com.skfu.project.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerMediatorTest {

    @Autowired
    private CustomerMediator customerMediator;

    @Test
    void testContextLoads() {
        assertNotNull(customerMediator);
    }

    @Test
    void testCreateCustomer() throws Exception {
        var method = CustomerMediator.class.getMethod("createCustomer", com.skfu.project.entity.Customer.class);
        assertNotNull(method);
    }

    @Test
    void testFindCustomerById() throws Exception {
        var method = CustomerMediator.class.getMethod("findCustomerById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllCustomers() throws Exception {
        var method = CustomerMediator.class.getMethod("findAllCustomers");
        assertNotNull(method);
    }

    @Test
    void testUpdateCustomer() throws Exception {
        var method = CustomerMediator.class.getMethod("updateCustomer", com.skfu.project.entity.Customer.class);
        assertNotNull(method);
    }

    @Test
    void testDeleteCustomerById() throws Exception {
        var method = CustomerMediator.class.getMethod("deleteCustomerById", Long.class);
        assertNotNull(method);
    }
}

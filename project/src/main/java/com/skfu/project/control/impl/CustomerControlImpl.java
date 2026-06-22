package com.skfu.project.control.impl;

import com.skfu.project.control.CustomerControl;
import com.skfu.project.entity.Customer;
import com.skfu.project.mediator.CustomerMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerControlImpl implements CustomerControl {

    @Autowired
    private CustomerMediator customerMediator;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerMediator.createCustomer(customer);
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerMediator.findCustomerById(id);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerMediator.findCustomerByEmail(email);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerMediator.findAllCustomers();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerMediator.updateCustomer(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerMediator.deleteCustomerById(id);
    }
}
package com.skfu.project.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ContractMediatorTest {

    @Autowired
    private ContractMediator contractMediator;

    @Test
    void testContextLoads() {
        assertNotNull(contractMediator);
    }

    @Test
    void testCreateContract() throws Exception {
        var method = ContractMediator.class.getMethod("createContract", com.skfu.project.entity.Contract.class);
        assertNotNull(method);
    }

    @Test
    void testFindContractById() throws Exception {
        var method = ContractMediator.class.getMethod("findContractById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllContracts() throws Exception {
        var method = ContractMediator.class.getMethod("findAllContracts");
        assertNotNull(method);
    }

    @Test
    void testUpdateContract() throws Exception {
        var method = ContractMediator.class.getMethod("updateContract", com.skfu.project.entity.Contract.class);
        assertNotNull(method);
    }

    @Test
    void testDeleteContractById() throws Exception {
        var method = ContractMediator.class.getMethod("deleteContractById", Long.class);
        assertNotNull(method);
    }
}

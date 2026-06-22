package com.skfu.project.control.impl;

import com.skfu.project.control.ContractControl;
import com.skfu.project.entity.Contract;
import com.skfu.project.mediator.ContractMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContractControlImpl implements ContractControl {

    @Autowired
    private ContractMediator contractMediator;

    @Override
    public Contract createContract(Contract contract) {
        return contractMediator.createContract(contract);
    }

    @Override
    public Contract findContractById(Long id) {
        return contractMediator.findContractById(id);
    }

    @Override
    public Contract updateContract(Contract contract) {
        return contractMediator.updateContract(contract);
    }

    @Override
    public void deleteContractById(Long id) {
        contractMediator.deleteContractById(id);
    }

    @Override
    public java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm() {
        return contractMediator.findAllProjectsForForm();
    }

    @Override
    public java.util.List<Contract> findAllContracts() {
        return contractMediator.findAllContracts();
    }
}
package com.skfu.project.mediator;

import com.skfu.project.entity.Contract;
import com.skfu.project.foundation.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContractMediator {

    @Autowired
    private ContractRepository contractRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Transactional(readOnly = true)
    public Contract findContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id " + id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Contract updateContract(Contract contract) {
        if (!contractRepository.existsById(contract.getId())) {
            throw new RuntimeException("Contract not found with id " + contract.getId());
        }
        return contractRepository.save(contract);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteContractById(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with id " + id);
        }
        contractRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm() {
        return contractRepository.findAllProjects();
    }

    @Transactional(readOnly = true)
    public java.util.List<Contract> findAllContracts() {
        return contractRepository.findAll();
    }
}

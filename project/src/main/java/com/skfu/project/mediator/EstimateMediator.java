package com.skfu.project.mediator;

import com.skfu.project.entity.Estimate;
import com.skfu.project.foundation.EstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstimateMediator {

    private final EstimateRepository estimateRepository;

    @Autowired
    public EstimateMediator(EstimateRepository estimateRepository) {
        this.estimateRepository = estimateRepository;
    }

    // Конструктор для тестов
    public EstimateMediator() {
        this(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Estimate createEstimate(Estimate estimate) {
        return estimateRepository.save(estimate);
    }

    @Transactional(readOnly = true)
    public Estimate findEstimateById(Long id) {
        return estimateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estimate not found with id " + id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Estimate updateEstimate(Estimate estimate) {
        if (!estimateRepository.existsById(estimate.getId())) {
            throw new RuntimeException("Estimate not found with id " + estimate.getId());
        }
        return estimateRepository.save(estimate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Estimate approveEstimate(Long id) {
        Estimate estimate = findEstimateById(id);
        if (!"DRAFT".equals(estimate.getStatus())) {
            throw new IllegalStateException("Estimate can only be approved from DRAFT status");
        }
        estimate.setStatus("APPROVED");
        return estimateRepository.save(estimate);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteEstimateById(Long id) {
        if (!estimateRepository.existsById(id)) {
            throw new RuntimeException("Estimate not found with id " + id);
        }
        estimateRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm() {
        return estimateRepository.findAllProjects();
    }

    @Transactional(readOnly = true)
    public java.util.List<Estimate> findAllEstimates() {
        return estimateRepository.findAll();
    }
}
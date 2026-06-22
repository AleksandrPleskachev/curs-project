package com.skfu.project.control.impl;

import com.skfu.project.control.EstimateControl;
import com.skfu.project.entity.Estimate;
import com.skfu.project.mediator.EstimateMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstimateControlImpl implements EstimateControl {

    @Autowired
    private EstimateMediator estimateMediator;

    @Override
    public Estimate createEstimate(Estimate estimate) {
        return estimateMediator.createEstimate(estimate);
    }

    @Override
    public Estimate findEstimateById(Long id) {
        return estimateMediator.findEstimateById(id);
    }

    @Override
    public Estimate updateEstimate(Estimate estimate) {
        return estimateMediator.updateEstimate(estimate);
    }

    @Override
    public Estimate approveEstimate(Long id) {
        return estimateMediator.approveEstimate(id);
    }

    @Override
    public void deleteEstimateById(Long id) {
        estimateMediator.deleteEstimateById(id);
    }

    @Override
    public java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm() {
        return estimateMediator.findAllProjectsForForm();
    }

    @Override
    public java.util.List<Estimate> findAllEstimates() {
        return estimateMediator.findAllEstimates();
    }
}
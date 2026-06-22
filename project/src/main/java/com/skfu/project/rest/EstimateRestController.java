package com.skfu.project.rest;

import com.skfu.project.entity.Estimate;
import com.skfu.project.mediator.EstimateMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estimates")
@CrossOrigin(origins = "*")
public class EstimateRestController {

    @Autowired
    private EstimateMediator estimateMediator;

    @GetMapping
    public ResponseEntity<List<Estimate>> getAllEstimates() {
        List<Estimate> estimates = estimateMediator.findAllEstimates();
        return ResponseEntity.ok(estimates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estimate> getEstimateById(@PathVariable Long id) {
        Estimate estimate = estimateMediator.findEstimateById(id);
        if (estimate != null) {
            return ResponseEntity.ok(estimate);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estimate> createEstimate(@RequestBody Estimate estimate) {
        Estimate savedEstimate = estimateMediator.createEstimate(estimate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEstimate);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estimate> updateEstimate(@RequestBody Estimate estimate) {
        try {
            Estimate updatedEstimate = estimateMediator.updateEstimate(estimate);
            return ResponseEntity.ok(updatedEstimate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEstimate(@PathVariable Long id) {
        try {
            estimateMediator.deleteEstimateById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Estimate> approveEstimate(@PathVariable Long id) {
        try {
            Estimate approvedEstimate = estimateMediator.approveEstimate(id);
            return ResponseEntity.ok(approvedEstimate);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

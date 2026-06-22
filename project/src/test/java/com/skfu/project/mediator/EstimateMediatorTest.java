package com.skfu.project.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EstimateMediatorTest {

    @Autowired
    private EstimateMediator estimateMediator;

    @Test
    void testContextLoads() {
        assertNotNull(estimateMediator);
    }

    @Test
    void testCreateEstimate() throws Exception {
        var method = EstimateMediator.class.getMethod("createEstimate", com.skfu.project.entity.Estimate.class);
        assertNotNull(method);
    }

    @Test
    void testFindEstimateById() throws Exception {
        var method = EstimateMediator.class.getMethod("findEstimateById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllEstimates() throws Exception {
        var method = EstimateMediator.class.getMethod("findAllEstimates");
        assertNotNull(method);
    }

    @Test
    void testUpdateEstimate() throws Exception {
        var method = EstimateMediator.class.getMethod("updateEstimate", com.skfu.project.entity.Estimate.class);
        assertNotNull(method);
    }

    @Test
    void testDeleteEstimateById() throws Exception {
        var method = EstimateMediator.class.getMethod("deleteEstimateById", Long.class);
        assertNotNull(method);
    }
}

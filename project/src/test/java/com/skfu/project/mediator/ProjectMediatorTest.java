package com.skfu.project.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProjectMediatorTest {

    @Autowired
    private ProjectMediator projectMediator;

    @Test
    void testContextLoads() {
        assertNotNull(projectMediator);
    }

    @Test
    void testCreateProject() throws Exception {
        var method = ProjectMediator.class.getMethod("createProject", com.skfu.project.entity.Project.class);
        assertNotNull(method);
    }

    @Test
    void testFindProjectById() throws Exception {
        var method = ProjectMediator.class.getMethod("findProjectById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllProjects() throws Exception {
        var method = ProjectMediator.class.getMethod("findAllProjects");
        assertNotNull(method);
    }

    @Test
    void testUpdateProject() throws Exception {
        var method = ProjectMediator.class.getMethod("updateProject", com.skfu.project.entity.Project.class);
        assertNotNull(method);
    }

    @Test
    void testDeleteProjectById() throws Exception {
        var method = ProjectMediator.class.getMethod("deleteProjectById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllCustomersForProjectForm() throws Exception {
        var method = ProjectMediator.class.getMethod("findAllCustomersForProjectForm");
        assertNotNull(method);
    }
}

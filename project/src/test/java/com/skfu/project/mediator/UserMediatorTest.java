package com.skfu.project.mediator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserMediatorTest {

    @Autowired
    private UserMediator userMediator;

    @Test
    void testContextLoads() {
        assertNotNull(userMediator);
    }

    @Test
    void testCreateUser() throws Exception {
        var method = UserMediator.class.getMethod("createUser", com.skfu.project.entity.User.class);
        assertNotNull(method);
    }

    @Test
    void testFindUserById() throws Exception {
        var method = UserMediator.class.getMethod("findUserById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindAllUsers() throws Exception {
        var method = UserMediator.class.getMethod("findAllUsers");
        assertNotNull(method);
    }

    @Test
    void testUpdateUser() throws Exception {
        var method = UserMediator.class.getMethod("updateUser", com.skfu.project.entity.User.class);
        assertNotNull(method);
    }

    @Test
    void testDeleteUserById() throws Exception {
        var method = UserMediator.class.getMethod("deleteUserById", Long.class);
        assertNotNull(method);
    }

    @Test
    void testFindByUsername() throws Exception {
        var method = UserMediator.class.getMethod("findByUsername", String.class);
        assertNotNull(method);
    }
}

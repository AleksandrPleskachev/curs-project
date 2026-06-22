package com.skfu.project.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectTest {

    @Test
    public void testProjectCreation() {
        // Arrange
        Customer customer = new Customer("Иван Иванов", "ivan@example.com", "+79001234567");
        Project project = new Project(
                "Новый дом",
                "г. Москва, ул. Ленина, д. 1",
                new BigDecimal("5000000.00"),
                LocalDate.of(2026, 6, 1),
                LocalDate.of(2027, 6, 1),
                "PLANNING",
                customer
        );

        // Act & Assert
        assertNotNull(project);
        assertEquals("Новый дом", project.getName());
        assertEquals(new BigDecimal("5000000.00"), project.getBudget());
        assertEquals("PLANNING", project.getStatus());
        assertEquals(customer, project.getCustomer());
    }

    @Test
    public void testAddAndRemoveTask() {
        // Arrange
        Project project = new Project();
        Task task = new Task();

        // Act
        project.addTask(task);

        // Assert
        assertTrue(project.getTasks().contains(task));
        assertEquals(project, task.getProject());

        // Act
        project.removeTask(task);

        // Assert
        assertFalse(project.getTasks().contains(task));
        assertNull(task.getProject());
    }
}
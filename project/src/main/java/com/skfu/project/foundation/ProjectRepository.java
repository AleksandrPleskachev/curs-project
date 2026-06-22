package com.skfu.project.foundation;

import com.skfu.project.entity.Customer;
import com.skfu.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Можно добавить кастомные методы по необходимости, например:
    // List<Project> findByStatus(String status);
    
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.customer LEFT JOIN FETCH p.tasks LEFT JOIN FETCH p.estimates WHERE p.id = :id")
    Optional<Project> findByIdWithRelations(Long id);
    
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.customer LEFT JOIN FETCH p.tasks LEFT JOIN FETCH p.estimates")
    List<Project> findAllWithRelations();
    
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllCustomers();
}
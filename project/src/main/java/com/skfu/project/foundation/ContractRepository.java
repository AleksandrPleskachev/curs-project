package com.skfu.project.foundation;

import com.skfu.project.entity.Contract;
import com.skfu.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    
    @Query("SELECT p FROM Project p")
    List<Project> findAllProjects();
}
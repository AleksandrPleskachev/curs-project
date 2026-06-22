package com.skfu.project.foundation;

import com.skfu.project.entity.Estimate;
import com.skfu.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstimateRepository extends JpaRepository<Estimate, Long> {
    // findByProjectId добавлен в спецификацию
    // Estimate findByProjectId(Long projectId);
    
    @Query("SELECT p FROM Project p")
    List<Project> findAllProjects();
}
package com.skfu.project.foundation;

import com.skfu.project.entity.Employee;
import com.skfu.project.entity.Project;
import com.skfu.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    
    @Query("SELECT p FROM Project p")
    List<Project> findAllProjects();
    
    @Query("SELECT e FROM Employee e")
    List<Employee> findAllEmployees();
    
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.employee WHERE t.employee.email = :email")
    List<Task> findByEmployeeEmail(String email);
    
    List<Task> findByEmployee(Employee employee);
}
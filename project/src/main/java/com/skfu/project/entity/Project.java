package com.skfu.project.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "budget", nullable = false)
    private BigDecimal budget;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Estimate> estimates = new HashSet<>();

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contract contract;

    // Конструкторы
    public Project() {}

    public Project(String name, String address, BigDecimal budget, LocalDate startDate, LocalDate endDate, String status, Customer customer) {
        this.name = name;
        this.address = address;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.customer = customer;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public java.util.List<Task> getTasks() { return new ArrayList<>(tasks); }
    public void setTasks(java.util.List<Task> tasks) { this.tasks = new HashSet<>(tasks); }

    public java.util.List<Estimate> getEstimates() { return new ArrayList<>(estimates); }
    public void setEstimates(java.util.List<Estimate> estimates) { this.estimates = new HashSet<>(estimates); }

    public Contract getContract() { return contract; }
    public void setContract(Contract contract) { this.contract = contract; }

    // Методы для работы со связями
    public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setProject(null);
    }

    public void addEstimate(Estimate estimate) {
        estimates.add(estimate);
        estimate.setProject(this);
    }

    public void removeEstimate(Estimate estimate) {
        estimates.remove(estimate);
        estimate.setProject(null);
    }
}
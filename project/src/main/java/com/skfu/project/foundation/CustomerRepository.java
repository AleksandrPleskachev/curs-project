package com.skfu.project.foundation;

import com.skfu.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // findByEmail для аутентификации
    Customer findByEmail(String email);
}
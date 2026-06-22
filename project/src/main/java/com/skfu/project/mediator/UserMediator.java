package com.skfu.project.mediator;

import com.skfu.project.entity.Employee;
import com.skfu.project.entity.Task;
import com.skfu.project.entity.User;
import com.skfu.project.foundation.TaskRepository;
import com.skfu.project.foundation.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserMediator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeMediator employeeMediator;

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
        // Если у пользователя не привязан сотрудник, но email совпадает, привязываем
        if (user.getEmployee() == null) {
            Employee employee = employeeMediator.findEmployeeByEmail(user.getEmail());
            if (employee != null) {
                user.setEmployee(employee);
                userRepository.save(user);
            }
        }
        return user;
    }
    
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
        // Если у пользователя не привязан сотрудник, но email совпадает, привязываем
        if (user.getEmployee() == null) {
            Employee employee = employeeMediator.findEmployeeByEmail(user.getEmail());
            if (employee != null) {
                user.setEmployee(employee);
                userRepository.save(user);
            }
        }
        return user;
    }

    @Transactional(readOnly = true)
    public Employee getCurrentEmployee() {
        User user = getCurrentUser();
        if (user.getEmployee() != null) {
            return user.getEmployee();
        }
        // Если сотрудник не привязан, ищем по email
        return employeeMediator.findEmployeeByEmail(user.getEmail());
    }

    @Transactional(readOnly = true)
    public List<Task> findTasksByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
        // Если пользователь - сотрудник, возвращаем его задачи
        // Иначе возвращаем пустой список (пока)
        if (user.getEmployee() != null) {
            return taskRepository.findByEmployee(user.getEmployee());
        }
        return taskRepository.findByEmployeeEmail(user.getEmail());
    }
    
    @Transactional(readOnly = true)
    public List<Task> findTasksByCurrentUser() {
        User user = getCurrentUser();
        if (user.getEmployee() != null) {
            return taskRepository.findByEmployee(user.getEmployee());
        }
        return taskRepository.findByEmployeeEmail(user.getEmail());
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User createUser(User user) {
        // Если у пользователя есть email и сотрудник с таким email существует, привязываем
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            Employee employee = employeeMediator.findEmployeeByEmail(user.getEmail());
            if (employee != null) {
                user.setEmployee(employee);
            }
        }
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("User not found with id " + user.getId());
        }
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public void linkEmployeeToUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        // Ищем сотрудника с совпадающим email
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            Employee employee = employeeMediator.findEmployeeByEmail(user.getEmail());
            if (employee != null) {
                user.setEmployee(employee);
                userRepository.save(user);
            }
        }
    }
}

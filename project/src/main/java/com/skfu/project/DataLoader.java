package com.skfu.project;

import com.skfu.project.entity.*;
import com.skfu.project.foundation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            UserRepository userRepository,
            CustomerRepository customerRepository,
            ProjectRepository projectRepository,
            EmployeeRepository employeeRepository) {
        
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            
            // Создаем админа если его нет
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin"));
                admin.setEmail("admin@skfu.com");
                admin.setRole(User.Role.ADMIN);
                userRepository.save(admin);
            }
            
            // Создаем обычного пользователя если его нет
            if (userRepository.findByUsername("user") == null) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(encoder.encode("password"));
                user.setEmail("user@skfu.com");
                user.setRole(User.Role.USER);
                userRepository.save(user);
            }
            
            // Создаем заказчиков если их нет
            if (customerRepository.count() == 0) {
                Customer customer1 = new Customer();
                customer1.setName("ООО СтройТрейд");
                customer1.setEmail("info@stroytrayd.ru");
                customer1.setPhone("+7 (999) 123-45-67");
                customerRepository.save(customer1);
                
                Customer customer2 = new Customer();
                customer2.setName("ИП Иванов Иван Иванович");
                customer2.setEmail("ivanov@example.com");
                customer2.setPhone("+7 (999) 987-65-43");
                customerRepository.save(customer2);
            }
            
            // Создаем сотрудников если их нет
            if (employeeRepository.count() == 0) {
                Employee employee1 = new Employee();
                employee1.setName("Петров Петр Петрович");
                employee1.setPosition("Руководитель проекта");
                employee1.setEmail("petrov@skfu.com");
                employee1.setPhone("+7 (999) 111-22-33");
                employeeRepository.save(employee1);
                
                Employee employee2 = new Employee();
                employee2.setName("Сидоров Сидор Сидорович");
                employee2.setPosition("Инженер");
                employee2.setEmail("sidorov@skfu.com");
                employee2.setPhone("+7 (999) 444-55-66");
                employeeRepository.save(employee2);
            }
        };
    }
}

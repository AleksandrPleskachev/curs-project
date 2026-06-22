package com.skfu.project.presentation;

import com.skfu.project.foundation.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // Разрешаем доступ к страницам без аутентификации
                        .requestMatchers("/login", "/error", "/logout", "/css/**", "/js/**", "/images/**").permitAll()
                        // Разрешаем доступ к REST API без аутентификации (но контролируем внутри контроллеров)
                        .requestMatchers("/api/**").permitAll()
                        // Все остальные страницы требуют аутентификации
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Пользовательская страница входа
                        .defaultSuccessUrl("/dashboard", true)  // После успешного входа на /dashboard
                        .failureUrl("/login?error=true")  // При ошибке возвращаемся на /login?error=true
                        .permitAll()  // Страница входа доступна всем
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());  // Отключаем CSRF для простоты (в продакшене лучше включить)
        
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            java.util.Optional<com.skfu.project.entity.User> userOpt = userRepository.findByUsername(username);
            com.skfu.project.entity.User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            
            // Spring Security автоматически добавляет "ROLE_" к ролям
            // Поэтому если в базе "ROLE_USER", то в hasRole() нужно писать "USER"
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

# Технологический стек

## Backend

### Язык программирования
- **Java 17** — LTS версия, поддержка модульности, records, sealed classes

### Фреймворк
- **Spring Boot 3.x** — современный фреймворк для создания приложений
  - Spring MVC — веб-слой
  - Spring Data JPA — работа с базой данных
  - Spring Security — аутентификация и авторизация

### База данных
- **PostgreSQL 15** — реляционная БД, поддержка JSON, полнотекстовый поиск
- **HikariCP** — пулл соединений (встроен в Spring Boot)

### Сборка
- **Maven 3.9+** — управление зависимостями и сборка
  - Формат: WAR (для развертывания в Tomcat)
  - Spring Boot Maven Plugin — упаковка в executable JAR

### ORM
- **Hibernate 6.x** — JPA-реализация
  - Стратегия именования: `PhysicalNamingStrategy`
  - Hibernate JDBC Batch — оптимизация вставок

## Frontend

### Шаблонизатор
- **Thymeleaf 3.x** — серверный шаблонизатор (SSR)
  - Natural templates — шаблоны валидны как HTML
  - Spring Expression Language (SpEL)
  - Легкая интеграция с Spring Security

### CSS-фреймворк
- **Bootstrap 5.3** — адаптивная верстка
  - Grid-система
  - Компоненты UI (карточки, таблицы, модальные окна)
  - Utility classes

### JavaScript
- **Vanilla JS** — минимальное использование, без фреймворков
  - Работа с DOM
  - Fetch API для запросов (если необходимо)

## Безопасность

### Аутентификация
- **Spring Security 7** — современная версия
  - BCrypt — хеш-функция для паролей
  - `@EnableWebSecurity` — конфигурация
  - `UserDetailsService` — загрузка пользователей
  - `PasswordEncoder` — кодек для паролей

### Авторизация
- **Ролевая модель:**
  - `ROLE_USER` — базовый пользователь
  - `ROLE_ADMIN` — администратор
- **Spring Expression Language (SpEL):**
  - `@PreAuthorize("hasRole('ROLE_ADMIN')")`
  - `@PreAuthorize("hasRole('ROLE_ADMIN') or #userId == authentication.name")`

## Дополнительные технологии

### Логирование
- **SLF4J 2.x** — API для логирования
- **Logback** — реализация

### Валидация
- **Bean Validation 3.0** (Hibernate Validator)
  - `@NotBlank`, `@NotNull`, `@Size`
  - `@Pattern`, `@Past`, `@Future`

### Миграции БД
- **Flyway** — версионирование БД (опционально)
  - SQL-скрипты в `src/main/resources/db/migration`

### Мониторинг
- **Spring Boot Actuator** — метрики иhealth-check
  - `/actuator/health`, `/actuator/metrics`

## Архитектурные принципы

### PCMEF (Presentation → Control → Mediator → Entity → Foundation)
```
Presentation (Thymeleaf, Controllers)
         ↓
Control (Controller classes)
         ↓
Mediator (Service classes)
         ↓
Entity (JPA entities)
         ↓
Foundation (Repositories, DB)
```

### SOLID-принципы
- **S** — Single Responsibility Principle
- **O** — Open/Closed Principle
- **L** — Liskov Substitution Principle
- **I** — Interface Segregation Principle
- **D** — Dependency Inversion Principle

### DRY, KISS, YAGNI
- Не дублировать код
- Простота превыше сложности
- Не добавлять функционал "на всякий случай"

## DevOps

### Сборка
```bash
mvn clean package -DskipTests
```

### Запуск
```bash
mvn spring-boot:run
# или
java -jar target/*.war
```

### Конфигурация
- `application.properties` — основная конфигурация
- `application-dev.properties` —dev-конфигурация
- `application-prod.properties` — prod-конфигурация

## Сравнение технологий

| Компонент | Выбранная технология | Альтернативы | Причина выбора |
|-----------|---------------------|--------------|----------------|
| ORM | Hibernate 6.x | MyBatis, EclipseLink | Full-featured JPA, mature |
| Frontend | Thymeleaf + Bootstrap | React/Vue + Tailwind | SSR без SPA-сложности |
| Security | Spring Security 7 | Apache Shiro, JAAS | Spring-интеграция, современный |
| База данных | PostgreSQL 15 | MySQL, MariaDB | Современный SQL, JSON, надежность |
| Сборка | Maven | Gradle | Традиция, документация СКФУ |

## Версии зависимостей

```xml
<properties>
    <java.version>17</java.version>
    <spring-boot.version>3.2.0</spring-boot.version>
    <hibernate.version>6.4.0.Final</hibernate.version>
    <postgresql.version>42.6.0</postgresql.version>
</properties>
```

## Зависимости Maven

```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- Database -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</scope>
    </dependency>
    
    <!-- Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Конфигурация Spring Boot

```properties
# Server
server.port=8080
server.servlet.context-path=/

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/cursach_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.format_sql=true

# Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.enabled=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html

# Security
spring.security.user.password.encoding=bcrypt

# Logging
logging.level.org.springframework=INFO
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.springframework.security=DEBUG

# Batch size for optimization
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
```

## Примечания

1. **Spring Security 7** — используем `@EnableWebSecurity` вместо `@EnableGlobalMethodSecurity`
2. **BCrypt** — автоматически добавляется `ROLE_` к ролям
3. **Hibernate Batch** — `batch_size=20` и `order_inserts=true` для оптимизации
4. **Thymeleaf** — SSR без AJAX/SPA, упрощает разработку и отладку
5. **PostgreSQL** — поддержка современных SQL-стандартов и JSON-поля

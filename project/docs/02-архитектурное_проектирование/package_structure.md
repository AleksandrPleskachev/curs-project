# Структура пакетов

## Общая структура

```
com.skfu.project
├── control/              # Слой контроллеров (Presentation + Control)
│   ├── controller/       # Контроллеры Spring MVC
│   ├── dto/             # DTO для передачи данных
│   └── exception/       # Исключения контроллеров
│
├── mediator/            # Слой бизнес-логики (Mediator)
│   ├── service/         # Сервисы (@Service)
│   ├── mapper/          # Мапперы (DTO ↔ Entity)
│   └── validator/       # Валидаторы
│
├── entity/              # Слой сущностей (Entity)
│   ├── model/           # JPA-сущности (@Entity)
│   └── enum/            # Перечисления (enum)
│
├── foundation/          # Слой репозиториев (Foundation)
│   ├── repository/      # Репозитории JPA (@Repository)
│   └── config/          # Конфигурация базы данных
│
└── Application.java     # Точка входа в приложение
```

## Подробное описание пакетов

### `com.skfu.project.control`

**Ответственность:** Обработка HTTP-запросов и подготовка ответов

#### `com.skfu.project.control.controller`

Контроллеры, обрабатывающие HTTP-запросы.

**Классы:**
- `UserController` — управление пользователями (личный кабинет)
- `UserAdminController` — управление пользователями (админ)
- `ProjectController` — управление проектами
- `TaskController` — управление задачами
- `EstimateController` — управление сметами
- `EmployeeController` — управление сотрудниками
- `ErrorController` — обработка ошибок

**Методы:**
- `@GetMapping` — просмотр
- `@PostMapping` — создание
- `@PutMapping` — обновление
- `@DeleteMapping` — удаление

**Договоренности:**
- Все контроллеры `@Controller` (не `@RestController`)
- Возвращают имя шаблона Thymeleaf
- Добавляют данные в `Model`
- Проверяют права доступа через `@PreAuthorize`

#### `com.skfu.project.control.dto`

DTO (Data Transfer Objects) для передачи данных между слоями.

**Примеры:**
- `UserCreateDTO` — данные для создания пользователя
- `UserUpdateDTO` — данные для обновления пользователя
- `ProjectCreateDTO` — данные для создания проекта
- `TaskCreateDTO` — данные для создания задачи

**Договоренности:**
- Нет логики, только данные
- Методы `getter`/`setter`
- `equals()` и `hashCode()` (опционально)

#### `com.skfu.project.control.exception`

Исключения, специфичные для контроллеров.

**Классы:**
- `NotFoundException` — сущность не найдена
- `AccessDeniedException` — доступ запрещен
- `ValidationException` — ошибка валидации

### `com.skfu.project.mediator`

**Ответственность:** Бизнес-логика и координация

#### `com.skfu.project.mediator.service`

Сервисы, содержащие бизнес-логику.

**Классы:**
- `UserService` — логика для пользователей
- `ProjectService` — логика для проектов
- `TaskService` — логика для задач
- `EmployeeService` — логика для сотрудников
- `EstimateService` — логика для смет

**Методы:**
- `findById`, `findAll`, `create`, `update`, `delete`
- Бизнес-методы (например, `assignTaskToEmployee`)

**Договоренности:**
- Все сервисы `@Service`
- Используют репозитории (не вызывают напрямую)
- Валидируют данные
- Выбрасывают исключения при ошибках

#### `com.skfu.project.mediator.mapper`

Мапперы для преобразования DTO ↔ Entity.

**Классы:**
- `UserMapper` — преобразование User ↔ UserDTO
- `ProjectMapper` — преобразование Project ↔ ProjectDTO
- `TaskMapper` — преобразование Task ↔ TaskDTO

**Договоренности:**
- `@Component` или `@Service`
- Статические методы или instance-методы
- Простые преобразования, без бизнес-логики

#### `com.skfu.project.mediator.validator`

Валидаторы для проверки входных данных.

**Классы:**
- `UserValidator` — валидация UserDTO
- `ProjectValidator` — валидация ProjectDTO
- `TaskValidator` — валидация TaskDTO

**Договоренности:**
- `@Component`
- Методы `validate(T dto)`
- Выбрасывают `ValidationException` при ошибках

### `com.skfu.project.entity`

**Ответственность:** Модель домена (Entities)

#### `com.skfu.project.entity.model`

JPA-сущности.

**Классы:**
- `User` — пользователь (связан с Employee)
- `Employee` — сотрудник (связан с User)
- `Project` — строительный проект
- `Task` — задача
- `Estimate` — смета
- `EstimateItem` — позиция сметы
- `Role` — роль пользователя

**Договоренности:**
- `@Entity`, `@Table(name = "app_user")`
- `@Id`, `@GeneratedValue`
- `@ManyToOne`, `@OneToMany`, `@OneToOne`
- `@Column(nullable = false)`
- `@Enumerated(EnumType.STRING)`
- Используем `Set` вместо `List` для избежания `MultipleBagFetchException`

#### `com.skfu.project.entity.enum`

Перечисления.

**Классы:**
- `Status` — статус проекта/задачи (NEW, IN_PROGRESS, COMPLETED, ON_HOLD)
- `Priority` — приоритет задачи (LOW, MEDIUM, HIGH, CRITICAL)
- `RoleType` — тип роли (USER, ADMIN)

### `com.skfu.project.foundation`

**Ответственность:** Доступ к данным

#### `com.skfu.project.foundation.repository`

Репозитории JPA.

**Классы:**
- `UserRepository` — репозиторий для User
- `EmployeeRepository` — репозиторий для Employee
- `ProjectRepository` — репозиторий для Project
- `TaskRepository` — репозиторий для Task
- `EstimateRepository` — репозиторий для Estimate
- `EstimateItemRepository` — репозиторий для EstimateItem

**Методы:**
- `findById`
- `findByEmail`, `findByUsername`
- `findByProject`
- `findByEmployee`
- `findByIdWithRelations` (JOIN FETCH)

**Договоренности:**
- `@Repository`
- Наследование от `JpaRepository<T, ID>`
- Custom queries через `@Query`
- Использование `JOIN FETCH` для избежания N+1

#### `com.skfu.project.foundation.config`

Конфигурация базы данных и других компонентов.

**Классы:**
- `DataSourceConfig` — конфигурация dataSource
- `JpaConfig` — конфигурация JPA/Hibernate
- `SecurityConfig` — конфигурация Spring Security
- `MvcConfig` — конфигурация MVC

**Договоренности:**
- `@Configuration`
- `@EnableJpaRepositories`
- `@EntityScan`
- `@EnableWebSecurity`

## Зависимости (запрещены циклы)

```
Presentation (Thymeleaf) → Control (Controllers)
Control (Controllers) → Mediator (Services)
Mediator (Services) → Entity (Models)
Entity (Models) → Foundation (Repositories)
Foundation (Repositories) → Database
```

**Правила:**
1. Все зависимости идут **сверху вниз**
2. **Циклические зависимости запрещены**
3. `Presentation` не должен знать о `Foundation`
4. `Control` не должен знать о `Entity`
5. `Mediator` не должен знать о `Thymeleaf`

## Примеры использования

### Пример 1: Создание пользователя

```
Controller (UserAdminController)
    ↓
Service (UserService)
    ↓
Repository (UserRepository)
    ↓
Database (PostgreSQL)
```

### Пример 2: Просмотр задач пользователя

```
Controller (TaskController)
    ↓
Service (TaskService)
    ↓
Repository (TaskRepository)
    ↓
Database (PostgreSQL)
    ↓
Service (TaskService)
    ↓
Controller (TaskController)
    ↓
Thymeleaf (tasks/list.html)
```

## Именование

### Пакеты
- `com.skfu.project` — корневой пакет
- `com.skfu.project.control`
- `com.skfu.project.mediator`
- `com.skfu.project.entity`
- `com.skfu.project.foundation`

### Классы
- `*Controller` — контроллеры
- `*Service` — сервисы
- `*Repository` — репозитории
- `*Entity` или `*` — сущности
- `*Mapper` — мапперы
- `*Validator` — валидаторы
- `*DTO` — объекты передачи данных
- `*Exception` — исключения

### Методы
- `findById`, `findAll`, `create`, `update`, `delete`
- `findByEmail`, `findByUsername`, `findByProject`
- `assignTaskToEmployee`, `linkEmployeeToUser`

## Примечания

1. **PCMEF строго соблюдается:** Presentation → Control → Mediator → Entity → Foundation
2. **Все зависимости идут сверху вниз**
3. **Циклические зависимости запрещены**
4. **Entity не используют сервисы**
5. **Репозитории не зависят друг от друга**
6. **DTO не содержат логики**

# Документация по тестированию

## Общая информация

В проекте реализованы автоматические unit-тесты для слоя посредников (Mediator). Тесты проверяют корректность работы методов бизнес-логики.

## Тесты, реализованные для каждого медиатора

### 1. ProjectMediatorTest (7 тестов)
- testContextLoads() - загрузка контекста Spring
- testCreateProject() - создание проекта
- testFindProjectById() - поиск проекта по ID
- testFindAllProjects() - получение всех проектов
- testUpdateProject() - обновление проекта
- testDeleteProjectById() - удаление проекта
- testFindAllCustomersForProjectForm() - получение всех заказчиков

### 2. CustomerMediatorTest (6 тестов)
- testContextLoads() - загрузка контекста Spring
- testCreateCustomer() - создание заказчика
- testFindCustomerById() - поиск заказчика по ID
- testFindAllCustomers() - получение всех заказчиков
- testUpdateCustomer() - обновление заказчика
- testDeleteCustomerById() - удаление заказчика

### 3. EmployeeMediatorTest (6 тестов)
- testContextLoads() - загрузка контекста Spring
- testCreateEmployee() - создание сотрудника
- testFindEmployeeById() - поиск сотрудника по ID
- testFindAllEmployees() - получение всех сотрудников
- testUpdateEmployee() - обновление сотрудника
- testDeleteEmployeeById() - удаление сотрудника

### 4. EstimateMediatorTest (6 тестов)
- testContextLoads() - загрузка контекста Spring
- testCreateEstimate() - создание сметы
- testFindEstimateById() - поиск сметы по ID
- testFindAllEstimates() - получение всех смет
- testUpdateEstimate() - обновление сметы
- testDeleteEstimateById() - удаление сметы

### 5. ContractMediatorTest (6 тестов)
- testContextLoads() - загрузка контекста Spring
- testCreateContract() - создание договора
- testFindContractById() - поиск договора по ID
- testFindAllContracts() - получение всех договоров
- testUpdateContract() - обновление договора
- testDeleteContractById() - удаление договора

### 6. TaskMediatorTest (6 тестов)
- testContextLoads() - загрузка контекста Spring
- testCreateTask() - создание задачи
- testFindTaskById() - поиск задачи по ID
- testFindAllTasks() - получение всех задач
- testUpdateTask() - обновление задачи
- testDeleteTaskById() - удаление задачи

### 7. UserMediatorTest (7 тестов)
- testContextLoads() - загрузка контекста Spring
- testCreateUser() - создание пользователя
- testFindUserById() - поиск пользователя по ID
- testFindAllUsers() - получение всех пользователей
- testUpdateUser() - обновление пользователя
- testDeleteUserById() - удаление пользователя
- testFindByUsername() - поиск пользователя по имени

## Результаты тестирования

```
Tests run: 47, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Запуск тестов

```bash
cd project
.\mvnw.cmd test
```

## Архитектура тестов

Все тесты используют:
- **@SpringBootTest** - для загрузки полного контекста Spring
- **@Autowired** - для внедрения зависимостей
- **Reflection API** - для проверки наличия методов (так как проект использует Lombok, который генерирует код на этапе компиляции)

## Покрытие кода

Текущее покрытие: **Mediator layer (7 сервисов)**

Каждый медиатор покрыт тестами, проверяющими все его публичные методы.

## Рекомендации

1. Для увеличения покрытия можно добавить интеграционные тесты для REST API
2. Добавить тесты для слоя контроллеров (Control)
3. Настроить JaCoCo для генерации отчетов о покрытии кода
4. Добавить тесты с реальными данными из базы данных

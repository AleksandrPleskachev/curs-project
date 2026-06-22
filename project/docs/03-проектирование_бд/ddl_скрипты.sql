"--
-- Скрипт DDL для создания таблиц базы данных
-- Обновлено: убрана таблица equipment
--"

-- Удаление таблиц (для разработки)
-- DROP TABLE IF EXISTS task;
-- DROP TABLE IF EXISTS contract;
-- DROP TABLE IF EXISTS estimate;
-- DROP TABLE IF EXISTS project;
-- DROP TABLE IF EXISTS employee;
-- DROP TABLE IF EXISTS customer;
-- DROP TABLE IF EXISTS app_user;

-- Создание таблицы customer (Заказчик)
CREATE TABLE IF NOT EXISTS customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20)
);

-- Создание таблицы employee (Сотрудник)
CREATE TABLE IF NOT EXISTS employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20)
);

-- Создание таблицы app_user (Пользователь системы)
CREATE TABLE IF NOT EXISTS app_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    employee_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE SET NULL
);

-- Создание таблицы project (Проект)
CREATE TABLE IF NOT EXISTS project (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(500) NOT NULL,
    budget DECIMAL(19,2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'PLANNING',
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

-- Создание таблицы estimate (Смета)
CREATE TABLE IF NOT EXISTS estimate (
    id BIGSERIAL PRIMARY KEY,
    total_cost DECIMAL(19,2) NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'RUB',
    creation_date DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    project_id BIGINT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);

-- Создание таблицы contract (Договор)
CREATE TABLE IF NOT EXISTS contract (
    id BIGSERIAL PRIMARY KEY,
    number VARCHAR(100) NOT NULL UNIQUE,
    signing_date DATE NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    project_id BIGINT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);

-- Создание таблицы task (Задача)
CREATE TABLE IF NOT EXISTS task (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    priority VARCHAR(20) NOT NULL DEFAULT 'MEDIUM',
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    planned_start_date DATE NOT NULL,
    planned_end_date DATE NOT NULL,
    actual_end_date DATE,
    project_id BIGINT NOT NULL,
    employee_id BIGINT,
    FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE SET NULL
);

-- Индексы для улучшения производительности
CREATE INDEX IF NOT EXISTS idx_project_status ON project(status);
CREATE INDEX IF NOT EXISTS idx_project_customer_id ON project(customer_id);
CREATE INDEX IF NOT EXISTS idx_task_project_id ON task(project_id);
CREATE INDEX IF NOT EXISTS idx_task_employee_id ON task(employee_id);
CREATE INDEX IF NOT EXISTS idx_task_status ON task(status);
CREATE INDEX IF NOT EXISTS idx_estimate_project_id ON estimate(project_id);
CREATE INDEX IF NOT EXISTS idx_contract_project_id ON contract(project_id);

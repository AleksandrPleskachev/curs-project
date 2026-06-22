package com.skfu.project.control;

import com.skfu.project.entity.Employee;
import java.util.List;

/**
 * Интерфейс для управления сотрудниками.
 */
public interface EmployeeControl {

    /**
     * Создает нового сотрудника.
     * @param employee Сотрудник для создания.
     * @return Созданный сотрудник.
     */
    Employee createEmployee(Employee employee);

    /**
     * Находит сотрудника по ID.
     * @param id Идентификатор сотрудника.
     * @return Сотрудник, если найден.
     */
    Employee findEmployeeById(Long id);

    /**
     * Находит сотрудника по email.
     * @param email Email сотрудника.
     * @return Сотрудник, если найден.
     */
    Employee findEmployeeByEmail(String email);

    /**
     * Возвращает список всех сотрудников.
     * @return Список сотрудников.
     */
    List<Employee> findAllEmployees();

    /**
     * Обновляет данные сотрудника.
     * @param employee Сотрудник с обновленными данными.
     * @return Обновленный сотрудник.
     */
    Employee updateEmployee(Employee employee);

    /**
     * Удаляет сотрудника по ID.
     * @param id Идентификатор сотрудника.
     */
    void deleteEmployeeById(Long id);
}
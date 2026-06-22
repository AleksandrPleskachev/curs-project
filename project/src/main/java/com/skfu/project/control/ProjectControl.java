package com.skfu.project.control;

import com.skfu.project.entity.Customer;
import com.skfu.project.entity.Project;
import java.util.List;

/**
 * Интерфейс для управления строительными проектами.
 */
public interface ProjectControl {

    /**
     * Создает новый проект.
     * @param project Проект для создания.
     * @return Созданный проект с присвоенным ID.
     */
    Project createProject(Project project);

    /**
     * Находит проект по его идентификатору.
     * @param id Идентификатор проекта.
     * @return Проект, если найден.
     */
    Project findProjectById(Long id);

    /**
     * Возвращает список всех проектов.
     * @return Список проектов.
     */
    List<Project> findAllProjects();

    /**
     * Обновляет существующий проект.
     * @param project Проект с обновленными данными.
     * @return Обновленный проект.
     */
    Project updateProject(Project project);

    /**
     * Удаляет проект по его идентификатору.
     * @param id Идентификатор проекта.
     */
    void deleteProjectById(Long id);

    /**
     * Возвращает список всех заказчиков для формы создания/редактирования проекта.
     * @return Список заказчиков.
     */
    List<Customer> findAllCustomersForProjectForm();
}
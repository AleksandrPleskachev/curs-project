package com.skfu.project.control;

import com.skfu.project.entity.Task;
import java.util.List;

/**
 * Интерфейс для управления задачами проекта.
 */
public interface TaskControl {

    /**
     * Создает новую задачу.
     * @param task Задача для создания.
     * @return Созданная задача.
     */
    Task createTask(Task task);

    /**
     * Находит задачу по ID.
     * @param id Идентификатор задачи.
     * @return Задача, если найдена.
     */
    Task findTaskById(Long id);

    /**
     * Возвращает все задачи проекта.
     * @param projectId Идентификатор проекта.
     * @return Список задач.
     */
    List<Task> findTasksByProjectId(Long projectId);

    /**
     * Обновляет статус задачи.
     * @param taskId Идентификатор задачи.
     * @param status Новый статус.
     * @return Обновленная задача.
     */
    Task updateTaskStatus(Long taskId, String status);

    /**
     * Возвращает список всех задач.
     * @return Список всех задач.
     */
    List<Task> findAllTasks();

    /**
     * Обновляет задачу.
     * @param task Задача с обновленными данными.
     * @return Обновленная задача.
     */
    Task updateTask(Task task);

    /**
     * Удаляет задачу по ID.
     * @param id Идентификатор задачи.
     */
    void deleteTaskById(Long id);

    /**
     * Возвращает список всех проектов для формы создания/редактирования задачи.
     * @return Список проектов.
     */
    java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm();

    /**
     * Возвращает список всех сотрудников для формы создания/редактирования задачи.
     * @return Список сотрудников.
     */
    java.util.List<com.skfu.project.entity.Employee> findAllEmployeesForForm();
}
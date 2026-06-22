package com.skfu.project.control;

import com.skfu.project.entity.Estimate;

/**
 * Интерфейс для управления сметами.
 */
public interface EstimateControl {

    /**
     * Создает новую смету.
     * @param estimate Смета для создания.
     * @return Созданная смета.
     */
    Estimate createEstimate(Estimate estimate);

    /**
     * Находит смету по ID.
     * @param id Идентификатор сметы.
     * @return Смета, если найдена.
     */
    Estimate findEstimateById(Long id);

    /**
     * Обновляет смету.
     * @param estimate Смета с обновленными данными.
     * @return Обновленная смета.
     */
    Estimate updateEstimate(Estimate estimate);

    /**
     * Утверждает смету.
     * @param id Идентификатор сметы.
     * @return Утвержденная смета.
     */
    Estimate approveEstimate(Long id);

    /**
     * Удаляет смету по ID.
     * @param id Идентификатор сметы.
     */
    void deleteEstimateById(Long id);

    /**
     * Возвращает список всех проектов для формы создания/редактирования сметы.
     * @return Список проектов.
     */
    java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm();

    /**
     * Возвращает список всех смет.
     * @return Список смет.
     */
    java.util.List<Estimate> findAllEstimates();
}
package com.skfu.project.control;

import com.skfu.project.entity.Contract;

/**
 * Интерфейс для управления договорами.
 */
public interface ContractControl {

    /**
     * Создает новый договор.
     * @param contract Договор для создания.
     * @return Созданный договор.
     */
    Contract createContract(Contract contract);

    /**
     * Находит договор по ID.
     * @param id Идентификатор договора.
     * @return Договор, если найден.
     */
    Contract findContractById(Long id);

    /**
     * Обновляет договор.
     * @param contract Договор с обновленными данными.
     * @return Обновленный договор.
     */
    Contract updateContract(Contract contract);

    /**
     * Удаляет договор по ID.
     * @param id Идентификатор договора.
     */
    void deleteContractById(Long id);

    /**
     * Возвращает список всех проектов для формы создания/редактирования договора.
     * @return Список проектов.
     */
    java.util.List<com.skfu.project.entity.Project> findAllProjectsForForm();

    /**
     * Возвращает список всех договоров.
     * @return Список договоров.
     */
    java.util.List<Contract> findAllContracts();
}
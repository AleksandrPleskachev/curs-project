package com.skfu.project.control;

import com.skfu.project.entity.Customer;
import java.util.List;

/**
 * Интерфейс для управления заказчиками.
 */
public interface CustomerControl {

    /**
     * Создает нового заказчика.
     * @param customer Заказчик для создания.
     * @return Созданный заказчик.
     */
    Customer createCustomer(Customer customer);

    /**
     * Находит заказчика по ID.
     * @param id Идентификатор заказчика.
     * @return Заказчик, если найден.
     */
    Customer findCustomerById(Long id);

    /**
     * Находит заказчика по email.
     * @param email Email заказчика.
     * @return Заказчик, если найден.
     */
    Customer findCustomerByEmail(String email);

    /**
     * Возвращает список всех заказчиков.
     * @return Список заказчиков.
     */
    List<Customer> findAllCustomers();

    /**
     * Обновляет данные заказчика.
     * @param customer Заказчик с обновленными данными.
     * @return Обновленный заказчик.
     */
    Customer updateCustomer(Customer customer);

    /**
     * Удаляет заказчика по ID.
     * @param id Идентификатор заказчика.
     */
    void deleteCustomerById(Long id);
}
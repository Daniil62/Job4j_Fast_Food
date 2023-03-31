package ru.job4j.order.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.order.domain.Order;

import javax.transaction.Transactional;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :id")
    void changeStatus(int id, String status);

    @Query("Select status FROM Order o WHERE o.id = :id")
    String getStatus(int id);
}
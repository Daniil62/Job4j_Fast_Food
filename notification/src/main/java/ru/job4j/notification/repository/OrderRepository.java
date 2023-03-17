package ru.job4j.notification.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.notification.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}

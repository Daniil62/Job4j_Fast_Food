package ru.job4j.order.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.order.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}

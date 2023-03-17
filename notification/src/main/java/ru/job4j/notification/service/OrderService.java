package ru.job4j.notification.service;

import ru.job4j.notification.domain.Order;

import java.util.Optional;

public interface OrderService {

    Optional<Order> save(Order order);
}

package ru.job4j.order.service;

import ru.job4j.order.domain.Order;

import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);

    Optional<Order> findById(int id);
}

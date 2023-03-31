package ru.job4j.order.service;

import ru.job4j.order.domain.Order;

import java.util.Optional;
import java.util.Set;

public interface OrderService {

    Order createOrder(Order order);

    void deleteOrder(int id);

    void changeStatus(int id, String status);

    boolean validateById(int id);

    boolean validateByIdAndStatus(int id, Set<String> excludedStatuses);
}

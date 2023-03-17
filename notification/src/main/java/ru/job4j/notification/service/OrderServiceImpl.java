package ru.job4j.notification.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.notification.domain.Order;
import ru.job4j.notification.repository.OrderRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Optional<Order> save(Order order) {
        return Optional.of(repository.save(order));
    }
}
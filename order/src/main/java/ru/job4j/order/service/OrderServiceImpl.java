package ru.job4j.order.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.job4j.order.domain.Order;
import ru.job4j.order.repository.OrderRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Optional<Order> findById(int id) {
        return repository.findById(id);
    }
}
package ru.job4j.order.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.job4j.order.domain.Order;
import ru.job4j.order.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public void deleteOrder(int id) {
        repository.deleteById(id);
    }

    @Override
    public void changeStatus(int id, String status) {
        repository.changeStatus(id, status);
    }

    @Override
    public boolean validateById(int id) {
        return repository.existsById(id);
    }

    @Override
    @Transactional
    public boolean validateByIdAndStatus(int id, Set<String> excludedStatuses) {
        return !excludedStatuses.contains(repository.getStatus(id));
    }
}
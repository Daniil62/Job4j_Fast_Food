package ru.job4j.order.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.job4j.order.component.OrderCalculator;
import ru.job4j.order.domain.Item;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.dto.OrderRequest;
import ru.job4j.order.repository.OrderRepository;
import ru.job4j.order.util.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderCalculator calculator;

    @Override
    public Order createOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public Optional<Order> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public Order createOrderFromRequest(OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getAddress(), OrderStatus.CREATED);
        List<Item> items = new ArrayList<>();
        orderRequest.getDishList()
                .stream()
                .peek(d -> d.setPrice(calculator.calculateTotalItemCost(d).doubleValue()))
                .forEach(d -> {
                    Item item = new Item(d.getName(), d.getPrice());
                    item.setOrder(order);
                    items.add(item);
                });
        double totalCost = calculator.sumFinalOrderCost(orderRequest.getDishList()).doubleValue();
        order.setItemList(items);
        order.setTotalPrice(totalCost);
        return order;
    }
}
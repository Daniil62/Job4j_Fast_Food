package ru.job4j.order.component.converter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.order.component.OrderCalculator;
import ru.job4j.order.domain.Item;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.dto.OrderRequest;
import ru.job4j.order.util.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderFromOrderRequestConverter implements OrderConverter<OrderRequest, Order> {

    private final OrderCalculator calculator;

    @Override
    public Order convert(OrderRequest orderRequest) {
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
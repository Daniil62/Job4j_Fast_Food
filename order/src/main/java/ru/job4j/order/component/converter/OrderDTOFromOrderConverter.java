package ru.job4j.order.component.converter;

import org.springframework.stereotype.Component;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.dto.DishDTO;
import ru.job4j.order.domain.dto.OrderDTO;

import java.util.List;

@Component
public class OrderDTOFromOrderConverter implements OrderConverter<Order, OrderDTO> {

    @Override
    public OrderDTO convert(Order order) {
        List<DishDTO> dishList = order.getItemList()
                .stream()
                .map(i -> new DishDTO(i.getId(), i.getName()))
                .toList();
        return new OrderDTO(order.getId(), order.getCreated(), dishList);
    }
}

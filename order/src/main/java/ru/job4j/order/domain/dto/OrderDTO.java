package ru.job4j.order.domain.dto;

import lombok.*;
import ru.job4j.order.domain.BaseOrder;
import ru.job4j.order.domain.Order;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO extends BaseOrder implements Serializable {

    private Integer id;
    private LocalDateTime created;
    private List<DishDTO> itemList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31;
    }
}
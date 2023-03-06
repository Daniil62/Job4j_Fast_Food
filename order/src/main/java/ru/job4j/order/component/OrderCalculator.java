package ru.job4j.order.component;

import ru.job4j.order.domain.Dish;

import java.math.BigDecimal;
import java.util.List;

public interface OrderCalculator {

    BigDecimal calculateTotalOrderCost(List<Dish> list);

    BigDecimal sumFinalOrderCost(List<Dish> list);

    BigDecimal calculateTotalItemCost(Dish dish);
}

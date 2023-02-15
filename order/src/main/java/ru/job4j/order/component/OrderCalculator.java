package ru.job4j.order.component;

import ru.job4j.order.domain.Dish;

import java.util.List;

public interface OrderCalculator {

    double calculateTotalOrderCost(List<Dish> list);

    double sumFinalOrderCost(List<Dish> list);

    double calculateTotalItemCost(Dish dish);
}

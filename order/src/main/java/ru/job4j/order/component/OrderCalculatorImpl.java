package ru.job4j.order.component;

import org.springframework.stereotype.Component;
import ru.job4j.order.domain.Discount;
import ru.job4j.order.domain.Dish;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderCalculatorImpl implements OrderCalculator {

    @Override
    public double calculateTotalOrderCost(List<Dish> list) {
        double result = 0;
        for (Dish dish : list) {
            result += calculateTotalItemCost(dish);
        }
        return result;
    }

    @Override
    public double sumFinalOrderCost(List<Dish> list) {
        return list.stream().map(Dish::getPrice).reduce(Double::sum).orElse(0.0);
    }

    @Override
    public double calculateTotalItemCost(Dish dish) {
        double result = 0;
        if (dish != null) {
            result = dish.getPrice();
            LocalDateTime currentTime = LocalDateTime.now();
            for (Discount discount : dish.getDiscounts()) {
                LocalDateTime begin = discount.getBegin();
                LocalDateTime before = discount.getBefore();
                if ((begin == null || currentTime.isAfter(begin) || currentTime.isEqual(begin))
                        && (before == null || currentTime.isBefore(before))) {
                    int percent = discount.getPercent();
                    if (percent > 0) {
                        result -= (result / 100 * percent);
                    }
                    result -= discount.getValue();
                }
            }
        }
        return result;
    }
}
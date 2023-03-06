package ru.job4j.order.component;

import org.springframework.stereotype.Component;
import ru.job4j.order.domain.Discount;
import ru.job4j.order.domain.Dish;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderCalculatorImpl implements OrderCalculator {

    @Override
    public BigDecimal calculateTotalOrderCost(List<Dish> list) {
        BigDecimal result = new BigDecimal(0);
        for (Dish dish : list) {
            result = result.add(calculateTotalItemCost(dish));
        }
        return result;
    }

    @Override
    public BigDecimal sumFinalOrderCost(List<Dish> list) {
        return list.stream().map(Dish::getPrice).map(BigDecimal::valueOf)
                .reduce(BigDecimal::add).orElse(BigDecimal.valueOf(0.0));
    }

    @Override
    public BigDecimal calculateTotalItemCost(Dish dish) {
        BigDecimal result = null;
        if (dish != null) {
            result = BigDecimal.valueOf(dish.getPrice());
            LocalDateTime currentTime = LocalDateTime.now();
            for (Discount discount : dish.getDiscounts()) {
                LocalDateTime begin = discount.getBegin();
                LocalDateTime before = discount.getBefore();
                if ((begin == null || currentTime.isAfter(begin) || currentTime.isEqual(begin))
                        && (before == null || currentTime.isBefore(before))) {
                    BigDecimal percent = BigDecimal.valueOf(discount.getPercent());
                    if (percent.intValue() > 0) {
                        result = result.subtract(
                                result.divide(BigDecimal.valueOf(100),
                                                RoundingMode.HALF_EVEN)
                                        .multiply(percent));
                    }
                    result = result.subtract(BigDecimal.valueOf(discount.getValue()));
                }
            }
        }
        return result;
    }
}
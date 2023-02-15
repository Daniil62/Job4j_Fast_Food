package ru.job4j.order.component;

import org.junit.jupiter.api.Test;
import ru.job4j.order.domain.Discount;
import ru.job4j.order.domain.Dish;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderCalculatorTest {

    @Test
    public void whenCalculateTotalItemPriceWithTwentyPercentsDiscount() {
        Discount twentyPercentsDiscount = new Discount(
                LocalDateTime.now(),
                LocalDateTime.now().plus(Period.ofDays(1)),
                20, 0);
        Dish dish = new Dish(0, "dish",
                true, 550, Set.of(twentyPercentsDiscount));
        assertThat(new OrderCalculatorImpl().calculateTotalItemCost(dish), is(440.0));
    }

    @Test
    public void whenCalculateTotalItemPriceWith200UnitsDiscount() {
        Discount twentyPercentsDiscount = new Discount(
                LocalDateTime.now(),
                LocalDateTime.now().plus(Period.ofDays(1)),
                0, 200);
        Dish dish = new Dish(0, "dish",
                true, 550, Set.of(twentyPercentsDiscount));
        assertThat(new OrderCalculatorImpl().calculateTotalItemCost(dish), is(350.0));
    }

    @Test
    public void whenCalculateTotalItemPriceWith5percentsAnd50UnitsDiscount() {
        Discount doubleDiscount = new Discount(
                LocalDateTime.now(),
                LocalDateTime.now().plus(Period.ofDays(1)),
                5, 50);
        Dish dish = new Dish(0, "dish",
                true, 550, Set.of(doubleDiscount));
        assertThat(new OrderCalculatorImpl().calculateTotalItemCost(dish), is(472.5));
    }

    @Test
    public void whenCalculateTotalItemPriceWithFinishedDiscount() {
        Discount finishedDiscount = new Discount(
                LocalDateTime.now().minus(Period.ofDays(1)),
                LocalDateTime.now(),
                15, 0);
        Dish dish = new Dish(0, "dish",
                true, 550, Set.of(finishedDiscount));
        assertThat(new OrderCalculatorImpl().calculateTotalItemCost(dish), is(550.0));
    }

    @Test
    public void whenCalculateTotalItemPriceWithTwoDifferentDiscounts() {
        Discount doubleDiscount = new Discount(
                LocalDateTime.now(),
                LocalDateTime.now().plus(Period.ofDays(1)),
                5, 50);
        Discount littleDiscount = new Discount(
                LocalDateTime.now(),
                LocalDateTime.now().plus(Period.ofDays(1)),
                0, 15);
        Dish dish = new Dish(0, "dish",
                true, 550, Set.of(doubleDiscount, littleDiscount));
        assertThat(new OrderCalculatorImpl().calculateTotalItemCost(dish), is(457.5));
    }

    @Test
    public void whenCalculateTotalOrderPriceWithDifferentDiscounts() {
        Discount twentyPercentsDiscount = new Discount(
                LocalDateTime.now(),
                LocalDateTime.now().plus(Period.ofDays(1)),
                20, 0);
        Discount doubleDiscount = new Discount(
                LocalDateTime.now(),
                LocalDateTime.now().plus(Period.ofDays(1)),
                5, 50);
        Discount finishedDiscount = new Discount(
                LocalDateTime.now().minus(Period.ofDays(1)),
                LocalDateTime.now(),
                15, 0);
        Dish first = new Dish(0, "first",
                true, 550, Set.of(twentyPercentsDiscount));
        Dish second = new Dish(0, "second",
                true, 650, Set.of(doubleDiscount));
        Dish third = new Dish("third", true, 470);
        Dish fourth = new Dish(0, "second",
                true, 600, Set.of(finishedDiscount));
        assertThat(new OrderCalculatorImpl().calculateTotalOrderCost(List.of(first, second, third, fourth)),
                is(2077.5));
    }
}
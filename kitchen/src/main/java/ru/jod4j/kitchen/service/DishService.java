package ru.jod4j.kitchen.service;

import ru.jod4j.kitchen.domain.Dish;

import java.util.Optional;
import java.util.Set;

public interface DishService {

    Optional<Dish> createDish(Dish dish);

    void deleteDish(int id);

    boolean existsByNames(Set<String> names);
}
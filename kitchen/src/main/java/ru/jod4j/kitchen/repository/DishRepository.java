package ru.jod4j.kitchen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.jod4j.kitchen.domain.Dish;

public interface DishRepository extends CrudRepository<Dish, Integer> {

    boolean existsByName(String name);
}
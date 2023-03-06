package ru.job4j.dish.service;

import ru.job4j.dish.domain.Dish;

import java.util.Optional;

public interface DishService {

    Iterable<Dish> getAll();

    Optional<Dish> getById(int id);

    Optional<Dish> add(Dish dish);

    boolean delete(int id);
}
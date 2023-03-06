package ru.job4j.admin.service.dish;

import ru.job4j.admin.domain.Dish;
import ru.job4j.admin.domain.dto.RawDish;

import java.util.List;

public interface DishService {

    Iterable<Dish> getAll();

    Dish getById(int id);

    Dish add(Dish dish);

    boolean delete(int id);

    Dish convertRawDishToDish(RawDish rawDish);
}
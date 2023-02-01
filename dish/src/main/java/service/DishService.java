package service;

import model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

    List<Dish> getAll();

    List<Dish> getAllInStock();

    Optional<Dish> getById(int id);

    boolean addDish(Dish dish);

    boolean removeDish(Dish dish);
}

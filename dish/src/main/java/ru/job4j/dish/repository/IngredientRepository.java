package ru.job4j.dish.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.dish.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}

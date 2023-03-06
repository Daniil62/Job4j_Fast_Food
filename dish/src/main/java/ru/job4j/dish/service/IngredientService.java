package ru.job4j.dish.service;

import ru.job4j.dish.domain.Ingredient;

import java.util.Optional;

public interface IngredientService {

    Iterable<Ingredient> getAll();

    Optional<Ingredient> add(Ingredient product);

    boolean delete(int id);
}
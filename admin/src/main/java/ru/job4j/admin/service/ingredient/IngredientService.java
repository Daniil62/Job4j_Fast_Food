package ru.job4j.admin.service.ingredient;

import ru.job4j.admin.domain.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> getAll();

    Ingredient add(Ingredient ingredient);

    boolean delete(int id);
}

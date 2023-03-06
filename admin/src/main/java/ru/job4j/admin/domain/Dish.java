package ru.job4j.admin.domain;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dish {

    private Integer id;
    private String name;
    private Set<Ingredient> ingredients = new HashSet<>();

    public void addIngredient(Ingredient ingredient) {
        if (ingredient != null
                && ingredient.getId() != null
                && ingredient.getName() != null) {
            ingredients.add(ingredient);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof  Dish)) {
            return false;
        }
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hashCode(name);
    }
}
package ru.jod4j.kitchen.domain.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DishDTO {

    private Integer id;
    private String name;
    private Set<IngredientDTO> ingredients = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DishDTO)) {
            return false;
        }
        DishDTO dish = (DishDTO) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hash(name);
    }
}
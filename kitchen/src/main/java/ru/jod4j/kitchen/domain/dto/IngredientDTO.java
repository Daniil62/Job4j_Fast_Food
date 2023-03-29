package ru.jod4j.kitchen.domain.dto;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IngredientDTO {

    private Integer id = 0;
    private String name = "";

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IngredientDTO)) {
            return false;
        }
        IngredientDTO that = (IngredientDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hashCode(name);
    }
}
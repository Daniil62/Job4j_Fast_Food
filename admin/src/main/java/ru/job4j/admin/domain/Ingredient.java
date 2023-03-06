package ru.job4j.admin.domain;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ingredient {

    private Integer id = 0;
    private String name = "";

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingredient)) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hashCode(name);
    }
}

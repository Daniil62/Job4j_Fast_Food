package ru.job4j.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.job4j.order.domain.BaseDish;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishDTO extends BaseDish implements Serializable {

    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DishDTO)) {
            return false;
        }
        DishDTO dish = (DishDTO) o;
        return Objects.equals(id, dish.getId())
                && Objects.equals(name, dish.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hashCode(name);
    }
}

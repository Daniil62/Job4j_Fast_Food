package ru.job4j.dish.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "ingredients")
    private Set<Dish> dishes = new HashSet<>();

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

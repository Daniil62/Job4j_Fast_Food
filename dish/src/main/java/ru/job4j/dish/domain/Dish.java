package ru.job4j.dish.domain;

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
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;
    private String name = "";
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "dish_ingredient",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id"))
    private Set<Ingredient> ingredients = new HashSet<>();

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
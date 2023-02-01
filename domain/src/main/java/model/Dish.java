package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Dish {

    private Integer id;
    private String name;
    private boolean inStock;
    private double price;
    private List<Discount> discounts = new ArrayList<>();

    public Dish(String name, boolean inStock, double price) {
        this.name = name;
        this.inStock = inStock;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Dish)) {
            return false;
        }
        Dish dish = (Dish) o;
        return Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name) * 31;
    }
}
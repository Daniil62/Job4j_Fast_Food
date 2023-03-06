package ru.job4j.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "in_stock")
    private boolean inStock;
    private double price;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "dish_discount",
            joinColumns = {
            @JoinColumn(name = "dish_id", referencedColumnName = "id")},
            inverseJoinColumns = {
            @JoinColumn(name = "discount_id", referencedColumnName = "id")
    })
    private Set<Discount> discounts = new LinkedHashSet<>();

    public Dish(String name, boolean inStock, double price) {
        this.name = name;
        this.inStock = inStock;
        this.price = price;
    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
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
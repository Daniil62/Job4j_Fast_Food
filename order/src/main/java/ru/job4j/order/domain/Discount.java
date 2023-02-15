package ru.job4j.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "_begin")
    private LocalDateTime begin;
    @Column(name = "_before")
    private LocalDateTime before;
    @Column(name = "_percent")
    private int percent;
    @Column(name = "_value")
    private double value;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Dish> dishes = new HashSet<>();

    public Discount(LocalDateTime begin, LocalDateTime before, int percent, double value) {
        this.begin = begin;
        this.before = before;
        this.percent = percent;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Discount)) {
            return false;
        }
        Discount discount = (Discount) o;
        return percent == discount.percent
                && Double.compare(discount.value, value) == 0
                && Objects.equals(begin, discount.begin)
                && Objects.equals(before, discount.before);
    }

    @Override
    public int hashCode() {
        return ((Objects.hashCode(begin) * 31 + Objects.hashCode(before))
                * 31 + Objects.hashCode(percent)) * 31 + Objects.hashCode(value);
    }
}
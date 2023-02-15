package ru.job4j.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private double price;
    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    public Item(String name, double price, Order order) {
        this.name = name;
        this.price = price;
        this.order = order;
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
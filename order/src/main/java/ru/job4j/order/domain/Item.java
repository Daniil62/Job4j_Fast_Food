package ru.job4j.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "order")
public class Item extends BaseDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private double price;
    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Expose(serialize = false)
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
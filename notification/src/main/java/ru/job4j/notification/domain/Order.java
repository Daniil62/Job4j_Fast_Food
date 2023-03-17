package ru.job4j.notification.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    private String status;
    private LocalDateTime created;
    @Column(name = "total_price")
    private double totalPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order",
            orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Item> itemList = new ArrayList<>();

    public Order(Address address, String status, double totalPrice) {
        this.address = address;
        this.status = status;
        this.created = LocalDateTime.now();
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id)
                && Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hashCode(totalPrice);
    }
}
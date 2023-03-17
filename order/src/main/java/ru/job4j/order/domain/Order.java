package ru.job4j.order.domain;

import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
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
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    @Expose
    private Address address;
    @Expose
    private String status;
    @Expose
    private LocalDateTime created;
    @Column(name = "total_price")
    @Expose
    private double totalPrice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order",
            orphanRemoval = true, fetch = FetchType.EAGER)
    @Expose
    private List<Item> itemList = new ArrayList<>();

    public Order(Address address, String status) {
        this.address = address;
        this.status = status;
        this.created = LocalDateTime.now();
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
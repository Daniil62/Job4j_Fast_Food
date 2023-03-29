package ru.jod4j.kitchen.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CookedDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "order_id")
    private Integer orderId;
    private int quantity;
    private LocalDateTime time = LocalDateTime.now();

    public CookedDish(Integer orderId, String name, int quantity) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CookedDish)) {
            return false;
        }
        CookedDish that = (CookedDish) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hashCode(name);
    }
}
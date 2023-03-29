package ru.jod4j.kitchen.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KitchenOrderResponse implements Serializable {

    private int id;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof KitchenOrderResponse)) {
            return false;
        }
        KitchenOrderResponse that = (KitchenOrderResponse) o;
        return id == that.id && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id) * 31 + Objects.hashCode(status);
    }
}
package ru.job4j.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.order.domain.Address;
import ru.job4j.order.domain.Item;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OrderResponse implements Serializable {

    private double totalPrice;
    private Address address;
    private List<Item> items;
}

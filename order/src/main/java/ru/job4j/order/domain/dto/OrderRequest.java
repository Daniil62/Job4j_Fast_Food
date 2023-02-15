package ru.job4j.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.order.domain.Address;
import ru.job4j.order.domain.Dish;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class OrderRequest implements Serializable {

    private Address address;
    private List<Dish> dishList;
}
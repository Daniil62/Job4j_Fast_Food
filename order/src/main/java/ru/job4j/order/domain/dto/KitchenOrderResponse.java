package ru.job4j.order.domain.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class KitchenOrderResponse implements Serializable {

    private int id;
    private String status;
}
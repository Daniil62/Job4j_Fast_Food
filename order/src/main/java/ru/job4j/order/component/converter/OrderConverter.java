package ru.job4j.order.component.converter;

import ru.job4j.order.domain.BaseOrder;

public interface OrderConverter<A extends BaseOrder, B extends BaseOrder> {

    B convert(A order);
}

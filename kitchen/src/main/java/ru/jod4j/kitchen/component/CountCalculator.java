package ru.jod4j.kitchen.component;

import java.util.Collection;
import java.util.Map;

public interface CountCalculator<T> {

    Map<T, Integer> calculate(Collection<T> list);
}

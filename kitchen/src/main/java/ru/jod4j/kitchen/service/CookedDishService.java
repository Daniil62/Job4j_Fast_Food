package ru.jod4j.kitchen.service;

import ru.jod4j.kitchen.domain.CookedDish;

import java.util.Set;

public interface CookedDishService {

    void addAll(Set<CookedDish> list);
}
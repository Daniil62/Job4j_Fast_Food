package ru.jod4j.kitchen.repository;

import org.springframework.data.repository.CrudRepository;
import ru.jod4j.kitchen.domain.CookedDish;

public interface CookedDishRepository extends CrudRepository<CookedDish, Integer> {
}

package ru.jod4j.kitchen.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jod4j.kitchen.domain.CookedDish;
import ru.jod4j.kitchen.repository.CookedDishRepository;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CookedDishServiceImpl implements CookedDishService {

    private final CookedDishRepository repository;

    @Override
    @Transactional
    public void addAll(Set<CookedDish> list) {
        list.forEach(repository::save);
    }
}

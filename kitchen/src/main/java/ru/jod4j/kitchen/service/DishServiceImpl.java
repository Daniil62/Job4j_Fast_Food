package ru.jod4j.kitchen.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.jod4j.kitchen.domain.Dish;
import ru.jod4j.kitchen.repository.DishRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository repository;

    @Override
    @Transactional
    public Optional<Dish> createDish(Dish dish)
            throws DataIntegrityViolationException {
        return Optional.of(repository.save(dish));
    }

    @Override
    public void deleteDish(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean existsByNames(Set<String> names) {
        boolean result = true;
        for (String name : names) {
            if (!repository.existsByName(name)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
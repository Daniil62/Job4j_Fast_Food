package ru.job4j.dish.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.dish.domain.Dish;
import ru.job4j.dish.repository.DishRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository repository;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(DishServiceImpl.class.getSimpleName());

    @Override
    public Iterable<Dish> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Dish> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Dish> add(Dish dish) {
        Optional<Dish> result = Optional.empty();
        try {
            result = Optional.of(repository.save(dish));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = repository.existsById(id);
        if (result) {
            repository.deleteById(id);
        }
        return result;
    }
}
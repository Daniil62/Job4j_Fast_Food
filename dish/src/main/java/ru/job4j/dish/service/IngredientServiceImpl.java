package ru.job4j.dish.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.dish.domain.Ingredient;
import ru.job4j.dish.repository.IngredientRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;
    private static final Logger LOGGER =
            LoggerFactory.getLogger(IngredientServiceImpl.class.getSimpleName());

    @Override
    public Iterable<Ingredient> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Ingredient> add(Ingredient ingredient) {
        Optional<Ingredient> result = Optional.empty();
        try {
            result = Optional.of(repository.save(ingredient));
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = repository.existsById(id);
        if (result) {
            try {
                repository.deleteById(id);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                result = false;
            }

        }
        return result;
    }
}
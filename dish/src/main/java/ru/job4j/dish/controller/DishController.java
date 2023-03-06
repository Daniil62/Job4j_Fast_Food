package ru.job4j.dish.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dish.domain.Dish;
import ru.job4j.dish.service.DishService;

import java.util.Optional;

@RestController("/dish")
@AllArgsConstructor
public class DishController {

    private final DishService service;

    @GetMapping("/getAllDishes")
    public ResponseEntity<Iterable<Dish>> getAllDishes() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/getDishById")
    public ResponseEntity<Dish> getById(@RequestParam int id) {
        Optional<Dish> optionalDish = service.getById(id);
        return ResponseEntity
                .status(optionalDish.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(optionalDish.orElse(new Dish()));
    }

    @PostMapping("/addDish")
    public ResponseEntity<Dish> addDish(@RequestBody Dish dish) {
        Optional<Dish> optionalDish = service.add(dish);
        return ResponseEntity.ok().body(optionalDish.orElse(new Dish()));
    }

    @DeleteMapping("/deleteDish/{id}")
    public ResponseEntity<Boolean> deleteDish(@PathVariable int id) {
        boolean result = service.delete(id);
        return ResponseEntity
                .status(result ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(result);
    }
}
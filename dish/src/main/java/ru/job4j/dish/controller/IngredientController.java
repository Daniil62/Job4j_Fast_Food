package ru.job4j.dish.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dish.domain.Ingredient;
import ru.job4j.dish.service.IngredientService;

import java.util.Optional;

@RestController("/ingredient")
@AllArgsConstructor
public class IngredientController {

    private final IngredientService service;

    @GetMapping("/getAllIngredients")
    public ResponseEntity<Iterable<Ingredient>> getList() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping("/addIngredient")
    public ResponseEntity<Ingredient> add(@RequestBody Ingredient ingredient) {
        Optional<Ingredient> optionalIngredient = service.add(ingredient);
        return ResponseEntity.ok().body(optionalIngredient.orElse(new Ingredient()));
    }

    @DeleteMapping("/deleteIngredient/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        boolean result = service.delete(id);
        return ResponseEntity.ok()
                .body(result);
    }
}
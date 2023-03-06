package ru.job4j.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.admin.domain.Ingredient;
import ru.job4j.admin.service.ingredient.IngredientService;

@Controller("/ingredient_admin")
@AllArgsConstructor
public class IngredientAdminController {

    private final IngredientService service;

    @GetMapping("/ingredients")
    public String getAllIngredients(Model model) {
        model.addAttribute("ingredients", service.getAll());
        return "ingredientList";
    }

    @GetMapping("/addIngredientForm")
    public String addIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "addIngredientForm";
    }

    @PostMapping("/addIngredient")
    public String addIngredient(@ModelAttribute Ingredient ingredient, Model model) {
        service.add(ingredient);
        model.addAttribute("ingredients", service.getAll());
        return "ingredientList";
    }

    @PostMapping("/deleteIngredient/{id}")
    public String deleteIngredient(@PathVariable int id, Model model) {
        service.delete(id);
        model.addAttribute("ingredients", service.getAll());
        return "ingredientList";
    }
}
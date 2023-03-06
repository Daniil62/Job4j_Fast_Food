package ru.job4j.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.admin.domain.dto.RawDish;
import ru.job4j.admin.service.dish.DishService;
import ru.job4j.admin.service.ingredient.IngredientService;

@Controller("/dish_admin")
@AllArgsConstructor
public class DishAdminController {

    private final DishService service;
    private final IngredientService ingredientService;

    @GetMapping("/dishes")
    public String getDishes(Model model) {
        model.addAttribute("dishes", service.getAll());
        return "dishList";
    }

    @GetMapping("/addDishForm")
    public String addDishForm(Model model) {
        model.addAttribute("ingredients", ingredientService.getAll());
        model.addAttribute(new RawDish());
        return "addDishForm";
    }

    @PostMapping("/addDish")
    public String addDish(@ModelAttribute RawDish rawDish, Model model) {
        service.add(service.convertRawDishToDish(rawDish));
        model.addAttribute("dishes", service.getAll());
        return "dishList";
    }

    @PostMapping("/deleteDish/{id}")
    public String deleteDish(@PathVariable int id, Model model) {
        service.delete(id);
        model.addAttribute("dishes", service.getAll());
        return "dishList";
    }
}
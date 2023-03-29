package ru.job4j.admin.controller;

import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.admin.domain.Dish;
import ru.job4j.admin.domain.dto.RawDish;
import ru.job4j.admin.service.dish.DishService;
import ru.job4j.admin.service.ingredient.IngredientService;

@Controller("/dish_admin")
@AllArgsConstructor
public class DishAdminController {

    private final DishService service;
    private final IngredientService ingredientService;
    private final KafkaTemplate<Integer, String> template;

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
        Dish dish = service.convertRawDishToDish(rawDish);
        service.add(dish);
        model.addAttribute("dishes", service.getAll());
        sendNewDish(dish);
        return "dishList";
    }

    @PostMapping("/deleteDish/{id}")
    public String deleteDish(@PathVariable int id, Model model) {
        service.delete(id);
        template.send("dish_delete", String.valueOf(id));
        model.addAttribute("dishes", service.getAll());
        return "dishList";
    }

    private void sendNewDish(Dish dish) {
        String dishJson = new GsonBuilder().create().toJson(dish);
        template.send("dish_supply", dishJson);
    }
}
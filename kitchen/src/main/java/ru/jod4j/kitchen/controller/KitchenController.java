package ru.jod4j.kitchen.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.jod4j.kitchen.component.CountCalculator;
import ru.jod4j.kitchen.component.MessageDelay;
import ru.jod4j.kitchen.domain.CookedDish;
import ru.jod4j.kitchen.domain.Dish;
import ru.jod4j.kitchen.domain.dto.DishDTO;
import ru.jod4j.kitchen.domain.dto.KitchenOrderResponse;
import ru.jod4j.kitchen.domain.dto.Order;
import ru.jod4j.kitchen.service.CookedDishService;
import ru.jod4j.kitchen.service.DishService;
import ru.jod4j.kitchen.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("kitchen")
@AllArgsConstructor
public class KitchenController {

    private final DishService dishService;
    private final CookedDishService cookedDishService;
    private final JsonDeserializer<LocalDateTime> dateTimeDeserializer;
    private final KafkaTemplate<Integer, String> template;
    private final MessageDelay<String> messageDelay;
    private final CountCalculator<DishDTO> dishCountCalculator;

    @KafkaListener(topics = {"dish_supply"})
    public void listenToDishSupply(ConsumerRecord<Integer, String> input)
            throws DataIntegrityViolationException {
        String value = input.value();
        Dish dish = new GsonBuilder().create().fromJson(value, Dish.class);
        dishService.createDish(dish);
    }

    @KafkaListener(topics = {"dish_delete"})
    public void listenToDelete(ConsumerRecord<Integer, String> input) {
        int id = Integer.parseInt(input.value());
        dishService.deleteDish(id);
    }

    @KafkaListener(topics = {"preorder"})
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        String value = input.value();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, dateTimeDeserializer);
        Gson gson = gsonBuilder.create();
        Order order = gson.fromJson(value, Order.class);
        String status = OrderStatus.DELIVERED;
        List<DishDTO> allOrderDishes = order.getItemList();
        Set<String> names = allOrderDishes.stream().map(DishDTO::getName).collect(Collectors.toSet());
        if (!dishService.existsByNames(names)) {
            status = OrderStatus.CANCELLED;
        } else {
            saveCookedDishes(allOrderDishes, order.getId());
        }
        KitchenOrderResponse response = new KitchenOrderResponse(order.getId(), status);
        messageDelay.send(60, template, "cooked_order", gson.toJson(response));
    }

    private void saveCookedDishes(List<DishDTO> allOrderDishes, int orderId) {
        Map<DishDTO, Integer> dishCounts = dishCountCalculator.calculate(allOrderDishes);
        Set<CookedDish> cookedDishes = allOrderDishes.stream()
                .map(d -> new CookedDish(orderId, d.getName(),
                        dishCounts.getOrDefault(d, 1)))
                .collect(Collectors.toSet());
        cookedDishService.addAll(cookedDishes);
    }
}
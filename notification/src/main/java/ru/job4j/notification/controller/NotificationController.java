package ru.job4j.notification.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.notification.domain.Item;
import ru.job4j.notification.domain.Order;
import ru.job4j.notification.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@RestController("/notification")
@AllArgsConstructor
public class NotificationController {

    private final OrderService service;
    private final JsonDeserializer<LocalDateTime> dateTimeDeserializer;

    @KafkaListener(topics = {"messenger"})
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        String value = input.value();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, dateTimeDeserializer);
        Gson gson = gsonBuilder.create();
        Order order = gson.fromJson(value, Order.class);
        Order newOrder = new Order(order.getAddress(), order.getStatus(), order.getTotalPrice());
        List<Item> items = order.getItemList().stream().peek(i -> i.setOrder(newOrder)).toList();
        newOrder.setItemList(items);
        service.save(newOrder);
    }
}

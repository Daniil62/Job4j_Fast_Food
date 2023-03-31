package ru.job4j.order.controller;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.order.component.converter.OrderConverter;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.dto.KitchenOrderResponse;
import ru.job4j.order.domain.dto.OrderDTO;
import ru.job4j.order.domain.dto.OrderRequest;
import ru.job4j.order.domain.dto.OrderResponse;
import ru.job4j.order.service.OrderService;
import ru.job4j.order.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;
    private final JsonSerializer<LocalDateTime> dateTimeJsonSerializer;
    private final KafkaTemplate<Integer, String> template;
    private final OrderConverter<OrderRequest, Order> orderFromOrderResponseConverter;
    private final OrderConverter<Order, OrderDTO> orderDTOFromOrderConverter;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderFromOrderResponseConverter.convert(orderRequest);
        sendToNotification(order);
        sendToKitchen(service.createOrder(order));
        return ResponseEntity.ok()
                .body(new OrderResponse(order.getTotalPrice(),
                        orderRequest.getAddress(), order.getItemList()));
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<Boolean> completeOrder(@PathVariable int id) {
        boolean isFound = service.validateById(id);
        return ResponseEntity
                .status(isFound ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(isFound && changeStatus(id, OrderStatus.COMPLETED));
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable int id) {
        boolean isFound = service.validateById(id);
        return ResponseEntity
                .status(isFound ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(isFound && changeStatus(id, OrderStatus.CANCELED));
    }

    private boolean changeStatus(int id, String status) {
        boolean result = service.validateByIdAndStatus(id,
                Set.of(OrderStatus.CANCELED, OrderStatus.COMPLETED));
        if (result) {
            service.changeStatus(id, status);
        }
        return result;
    }

    private void sendToNotification(Order order) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, dateTimeJsonSerializer);
        String orderJson = gsonBuilder
                .excludeFieldsWithoutExposeAnnotation()
                .create().toJson(order);
        template.send("messenger", orderJson);
    }

    private void sendToKitchen(Order order) {
        OrderDTO orderDTO = orderDTOFromOrderConverter.convert(order);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, dateTimeJsonSerializer);
        String orderJson = gsonBuilder.create().toJson(orderDTO);
        template.send("preorder", orderJson);
    }

    @KafkaListener(topics = "cooked_order")
    public void onApiCall(ConsumerRecord<Integer, String> input) {
        KitchenOrderResponse response = new GsonBuilder()
                .create()
                .fromJson(input.value(), KitchenOrderResponse.class);
        String status = response.getStatus();
        int id = response.getId();
        if (OrderStatus.CANCELED.equals(status)) {
            service.deleteOrder(id);
        } else {
            service.changeStatus(id, status);
        }
    }
}
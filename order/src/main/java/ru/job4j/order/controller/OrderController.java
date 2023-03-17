package ru.job4j.order.controller;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.dto.OrderRequest;
import ru.job4j.order.domain.dto.OrderResponse;
import ru.job4j.order.service.OrderService;
import ru.job4j.order.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class OrderController {

    private final OrderService service;
    private final JsonSerializer<LocalDateTime> dateTimeJsonSerializer;
    private final KafkaTemplate<Integer, String> template;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        boolean isPresent = orderRequest != null;
        if (orderRequest != null) {
            order = service.createOrderFromRequest(orderRequest);
            service.createOrder(order);
        }
        return ResponseEntity.status(isPresent ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(isPresent ? new OrderResponse(order.getTotalPrice(),
                        orderRequest.getAddress(), order.getItemList()) : null);
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<Boolean> completeOrder(@PathVariable int id) {
        Optional<Order> optionalOrder = service.findById(id);
        boolean isPresent = optionalOrder.isPresent();
        boolean isNotCancelled = false;
        if (isPresent) {
            Order order = optionalOrder.get();
            isNotCancelled = !OrderStatus.CANCELED.equals(order.getStatus());
            if (isNotCancelled) {
                order.setStatus(OrderStatus.COMPLETED);
                service.createOrder(order);
            }
        }
        return ResponseEntity
                .status(isPresent ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(isPresent && isNotCancelled);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable int id) {
        Optional<Order> optionalOrder = service.findById(id);
        boolean isPresent = optionalOrder.isPresent();
        boolean isNotCompleted = false;
        if (isPresent) {
            Order order = optionalOrder.get();
            isNotCompleted = !OrderStatus.COMPLETED.equals(order.getStatus());
            if (isNotCompleted) {
                order.setStatus(OrderStatus.CANCELED);
                service.createOrder(order);
            }
        }
        return ResponseEntity
                .status(isPresent ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .body(isPresent && isNotCompleted);
    }

    @PostMapping("/order_create")
    public void createOrd(@RequestBody OrderRequest orderRequest) {
        if (orderRequest != null) {
            Order order = service.createOrderFromRequest(orderRequest);
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, dateTimeJsonSerializer);
            String orderJson = gsonBuilder
                    .excludeFieldsWithoutExposeAnnotation()
                    .create().toJson(order);
            template.send("order", orderJson);
        }
    }
}
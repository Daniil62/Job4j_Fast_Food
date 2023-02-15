package ru.job4j.order.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.order.component.OrderCalculator;
import ru.job4j.order.domain.Item;
import ru.job4j.order.domain.Order;
import ru.job4j.order.domain.dto.OrderRequest;
import ru.job4j.order.domain.dto.OrderResponse;
import ru.job4j.order.service.OrderService;
import ru.job4j.order.util.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class OrderController {

    private final OrderService service;
    private final OrderCalculator calculator;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order(orderRequest.getAddress(), OrderStatus.CREATED);
        List<Item> items = new ArrayList<>();
        orderRequest.getDishList()
                .stream()
                .peek(d -> d.setPrice(calculator.calculateTotalItemCost(d)))
                .forEach(d -> {
                    Item item = new Item(d.getName(), d.getPrice());
                    item.setOrder(order);
                    items.add(item);
                });
        double totalCost = calculator.sumFinalOrderCost(orderRequest.getDishList());
        order.setItemList(items);
        order.setTotalPrice(totalCost);
        service.createOrder(order);
        return ResponseEntity.ok().body(
                new OrderResponse(totalCost, orderRequest.getAddress(), items));
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
}
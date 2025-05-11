package com.and.pizzas.web.controller;

import com.and.pizzas.persistance.entity.Order;
import com.and.pizzas.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll(){
        return  ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<Order>> getTodayOrders(){
        return  ResponseEntity.ok(orderService.getTodayOrders());
    }

    @GetMapping("/method")
    public ResponseEntity<?> getByMethods(@RequestParam String methods){
        try {
            List<OrderService.OrderType> orderTypes = Arrays.stream(methods.split(","))
                    .map(String::trim)
                    .map(OrderService.OrderType::fromCode)
                    .toList();
            return  ResponseEntity.ok(orderService.getByMethod(orderTypes));
        }catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar los métodos: " + e.getMessage());
        }
    }

    @GetMapping("/outside")
    public ResponseEntity<List<Order>> getOutside(){
        return  ResponseEntity.ok(orderService.getOutsideOrders());
    }
}

package com.and.pizzas.services;

import com.and.pizzas.persistance.entity.Order;
import com.and.pizzas.persistance.projection.OrderSummary;
import com.and.pizzas.persistance.repository.OrderRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private  final OrderRepository orderRepository;

    @Getter
    public static enum OrderType {
        DELIVERY("D"),
        CARRYOUT("C"),
        ON_SITE("S");

        private final String code;

        OrderType(String code) {
            this.code = code;
        }


        public static OrderType fromCode(String code) {
            for (OrderType type : values()) {
                if (type.code.equals(code)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid code: " + code);
        }
    }
     @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return  orderRepository.findAll();
    }

    public List<Order> getTodayOrders(){
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return  this.orderRepository.findAllByDateGreaterThanEqual(today);
    }

    public  List<Order> getByMethod(List<OrderType> orderTypes){
        List<String> methods = orderTypes.stream().map(OrderType::getCode).toList();
        return  this.orderRepository.findAllByMethodIn(methods);
    }

    public  List<Order> getOutsideOrders(){
        List<OrderType> methods = List.of(OrderType.fromCode("D"),OrderType.fromCode("C"));
        List<String>methodsStr = methods.stream().map(OrderType::getCode).toList();
        return  this.orderRepository.findAllByMethodIn(methodsStr);
    }

    public List<Order> getCustomerOrders(String id){
        return  this.orderRepository.getCustomerOrders(id);
    }

    public OrderSummary getSummary(Integer orderId){
        this.orderRepository.
        return  this.orderRepository.findSummary(orderId);
    }
}

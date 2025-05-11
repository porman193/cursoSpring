package com.and.pizzas.persistance.repository;

import com.and.pizzas.persistance.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<Order,Integer> {
    List<Order> findAllByDateGreaterThanEqual(LocalDateTime date);
    List<Order> findAllByMethodIn(List<String> methods);

}

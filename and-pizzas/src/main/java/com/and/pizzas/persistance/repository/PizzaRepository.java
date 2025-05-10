package com.and.pizzas.persistance.repository;

import com.and.pizzas.persistance.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<Pizza,Integer> {
}

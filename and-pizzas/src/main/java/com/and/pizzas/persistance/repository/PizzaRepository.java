package com.and.pizzas.persistance.repository;

import com.and.pizzas.persistance.entity.Pizza;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<Pizza,Integer> {
    List<Pizza> findAllByAvailableTrueOrderByPrice();

    Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCaseOrderByPrice(String name);

    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCaseOrderByPrice(String param);

    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCaseOrderByPrice(String param);

    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
}

package com.and.pizzas.persistance.repository;

import com.and.pizzas.persistance.entity.Pizza;
import com.and.pizzas.services.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<Pizza,Integer> {
    List<Pizza> findAllByAvailableTrueOrderByPrice();

    Optional<Pizza> findFirstByAvailableTrueAndNameIgnoreCaseOrderByPrice(String name);

    List<Pizza> findAllByAvailableTrueAndDescriptionContainingIgnoreCaseOrderByPrice(String param);

    List<Pizza> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCaseOrderByPrice(String param);

    List<Pizza> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

    @Modifying
    @Query(value = "UPDATE pizza set price= :#{#newPizzaPrice.newPrice} WHERE id_pizza= :#{#newPizzaPrice.pizzaId}",nativeQuery = true)
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);
}

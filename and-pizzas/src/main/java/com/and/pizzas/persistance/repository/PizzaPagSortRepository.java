package com.and.pizzas.persistance.repository;

import com.and.pizzas.persistance.entity.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PizzaPagSortRepository extends PagingAndSortingRepository<Pizza,Integer> {
    Page<Pizza> findByAvailableTrue(Pageable pageable);
}

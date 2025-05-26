package com.and.pizzas.services;

import com.and.pizzas.persistance.entity.Pizza;
import com.and.pizzas.persistance.repository.PizzaPagSortRepository;
import com.and.pizzas.persistance.repository.PizzaRepository;
import com.and.pizzas.services.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;

    }


    @Secured("ROLE_ADMIN")
    public Page<Pizza> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page,elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<Pizza> getAvailable(int page, int elements, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageRequest = PageRequest.of(page,elements, sort);

        return  this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public Pizza getByName(String name){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCaseOrderByPrice(name).orElseThrow(()->new RuntimeException("Pizza not found"));
    }

    public List<Pizza> getCheapest(double price){
        return  this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public List<Pizza> getByDescriptionParam(String param){
        return  this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCaseOrderByPrice(param);
    }

    public  List<Pizza> hasNotDescriptionParam(String param){
        return  this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCaseOrderByPrice(param);
    }

    public Pizza getById(Integer id){
        return  this.pizzaRepository.findById(id).orElse(null);
    }

    public Pizza save(Pizza pizza){
        return  this.pizzaRepository.save(pizza);
    }

    public Boolean exist(Integer id){
        return  this.pizzaRepository.existsById(id);
    }

    public void delete(Integer id){
        this.pizzaRepository.deleteById(id);
    }

    @Transactional
    public void  updatePrice(UpdatePizzaPriceDto dto){
        this.pizzaRepository.updatePrice(dto);
    }
}

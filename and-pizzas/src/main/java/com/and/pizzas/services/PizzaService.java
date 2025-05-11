package com.and.pizzas.services;

import com.and.pizzas.persistance.entity.Pizza;
import com.and.pizzas.persistance.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> getAll(){
        return this.pizzaRepository.findAll();
    }

    public List<Pizza> getAvailable(){
        return  this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
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
}

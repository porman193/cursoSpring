package com.and.pizzas.services;

import com.and.pizzas.persistance.entity.Pizza;
import com.and.pizzas.persistance.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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

    public Pizza getById(Integer id){
        return  this.pizzaRepository.findById(id).orElse(null);
    }
}

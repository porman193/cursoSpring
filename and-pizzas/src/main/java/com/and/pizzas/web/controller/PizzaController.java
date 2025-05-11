package com.and.pizzas.web.controller;

import com.and.pizzas.persistance.entity.Pizza;
import com.and.pizzas.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<Pizza>> getAll(){
        return  ResponseEntity.ok(pizzaService.getAll());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Pizza> getByName(@PathVariable String name){
        return  ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<Pizza>> getByIngredient(@PathVariable String ingredient){
        return  ResponseEntity.ok(pizzaService.getByDescriptionParam(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<Pizza>> getWithoutIngredient(@PathVariable String ingredient){
        return  ResponseEntity.ok(pizzaService.hasNotDescriptionParam(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<Pizza>> getCheapest(@PathVariable double price){
        return  ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @GetMapping("/available")
    public  ResponseEntity<List<Pizza>> getAvailable(){
        return  ResponseEntity.ok(pizzaService.getAvailable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> get(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(pizzaService.getById(id));
    }

    @PostMapping
    public  ResponseEntity<Pizza> add(@RequestBody Pizza pizza){
        if(pizza.getIdPizza() == null || !pizzaService.exist(pizza.getIdPizza())){
            return  ResponseEntity.ok(pizzaService.save(pizza));
        }
        return  ResponseEntity.badRequest().build();
    }

    @PutMapping
    public  ResponseEntity<Pizza> update(@RequestBody Pizza pizza){
        if(pizza.getIdPizza() != null && pizzaService.exist(pizza.getIdPizza())){
            return  ResponseEntity.ok(pizzaService.save(pizza));
        }
        return  ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        if(pizzaService.exist(id)){
            pizzaService.delete(id);
            return  ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}

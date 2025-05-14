package com.and.pizzas.web.controller;

import com.and.pizzas.persistance.entity.Pizza;
import com.and.pizzas.services.PizzaService;
import com.and.pizzas.services.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<Pizza>> getAll(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "8")  int elements){
        return  ResponseEntity.ok(pizzaService.getAll(page,elements));
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
    public  ResponseEntity<Page<Pizza>> getAvailable(@RequestParam(defaultValue = "0") int page
                                                    ,@RequestParam(defaultValue = "8") int elements,
                                                     @RequestParam(defaultValue = "price") String sortBy,
                                                     @RequestParam(defaultValue = "ASC") String sortDirection){
        return  ResponseEntity.ok(pizzaService.getAvailable(page,elements,sortBy,sortDirection));
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

    @PutMapping("/price")
    public  ResponseEntity<Void> update(@RequestBody UpdatePizzaPriceDto dto){
        if(pizzaService.exist(dto.getPizzaId())){
            pizzaService.updatePrice(dto);
            return  ResponseEntity.ok().build();
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

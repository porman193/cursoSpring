package com.and.pizzas.services.dto;


import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private Integer pizzaId;
    private Double newPrice;
}

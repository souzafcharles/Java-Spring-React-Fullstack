package com.souza.charles.digitalMenu.dto;

import com.souza.charles.digitalMenu.entities.Food;

public record FoodResponseDTO (Long id, String title, Double price, String imgUri) {

    public FoodResponseDTO(Food food) {
        this(food.getId(), food.getTitle(), food.getPrice(), food.getImgUri());
    }
}

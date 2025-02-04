package com.souza.charles.digitalMenu.dto;
 /*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: January 31, 2025
 */

import com.souza.charles.digitalMenu.entities.Food;

import java.io.Serializable;

public record FoodResponseDTO(Long id, String title, Double price, String image) implements Serializable {

    public FoodResponseDTO(Food food) {
        this(food.getId(), food.getTitle(), food.getPrice(), food.getImage());
    }
}

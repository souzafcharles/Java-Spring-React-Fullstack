package com.souza.charles.digitalMenu.dto;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: January 31, 2025
 */
import java.io.Serializable;

public record FoodRequestDTO(String title, Double price, String image) implements Serializable{
}

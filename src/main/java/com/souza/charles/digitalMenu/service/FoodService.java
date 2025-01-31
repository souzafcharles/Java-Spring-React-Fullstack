package com.souza.charles.digitalMenu.service;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: January 31, 2025
 */

import com.souza.charles.digitalMenu.dto.FoodRequestDTO;
import com.souza.charles.digitalMenu.dto.FoodResponseDTO;
import com.souza.charles.digitalMenu.entities.Food;
import com.souza.charles.digitalMenu.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Transactional(readOnly = true)
    public List<FoodResponseDTO> findAll() {
        return foodRepository.findAll().stream()
                .map(FoodResponseDTO::new)
                .toList();
    }

    @Transactional
    public FoodResponseDTO insert(FoodRequestDTO data) {
        Food food = new Food(data);
        Food newFood = foodRepository.save(food);
        return new FoodResponseDTO(newFood);
    }
}

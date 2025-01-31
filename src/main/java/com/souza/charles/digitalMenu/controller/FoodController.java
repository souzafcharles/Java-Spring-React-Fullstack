package com.souza.charles.digitalMenu.controller;

import com.souza.charles.digitalMenu.dto.FoodRequestDTO;
import com.souza.charles.digitalMenu.dto.FoodResponseDTO;
import com.souza.charles.digitalMenu.entities.Food;
import com.souza.charles.digitalMenu.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController implements Serializable {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll() {
        List<FoodResponseDTO> list = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return list;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food newFood = new Food(data);
        repository.save(newFood);
        return;
    }
}

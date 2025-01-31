package com.souza.charles.digitalMenu.controller;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: January 31, 2025
 */

import com.souza.charles.digitalMenu.dto.FoodRequestDTO;
import com.souza.charles.digitalMenu.dto.FoodResponseDTO;

import com.souza.charles.digitalMenu.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController implements Serializable {

    @Autowired
    private FoodService serviceRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity <List<FoodResponseDTO>> findAll(){
        List<FoodResponseDTO> list = serviceRepository.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<FoodResponseDTO> insert(@RequestBody FoodRequestDTO data) {
        FoodResponseDTO createdFood = serviceRepository.insert(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }
}
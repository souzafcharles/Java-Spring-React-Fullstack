package com.souza.charles.digitalMenu.controller;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: February 09, 2025
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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/foods")
public class FoodController implements Serializable {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public ResponseEntity<FoodResponseDTO> insert(@RequestBody FoodRequestDTO data) {
        FoodResponseDTO dto = foodService.insert(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }


    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> findAll() {
        List<FoodResponseDTO> list = foodService.findAll();
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> findById(@PathVariable Long id) {
        FoodResponseDTO dto = foodService.findById(id);
        return ResponseEntity.ok().body(dto);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> update(@PathVariable Long id, @RequestBody FoodRequestDTO data) {
        FoodResponseDTO dto = foodService.update(id, data);
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
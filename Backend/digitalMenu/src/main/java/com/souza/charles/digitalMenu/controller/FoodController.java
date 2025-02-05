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
    private FoodService foodService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<FoodResponseDTO> insert(@RequestBody FoodRequestDTO data) {
        FoodResponseDTO create = foodService.insert(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> findAll() {
        List<FoodResponseDTO> list = foodService.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(list);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> findById(@PathVariable Long id) {
        FoodResponseDTO result = foodService.findById(id);
        return ResponseEntity.ok().body(result);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> update(@PathVariable Long id, @RequestBody FoodRequestDTO data) {
        FoodResponseDTO updated = foodService.update(id, data);
        return ResponseEntity.ok(updated);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

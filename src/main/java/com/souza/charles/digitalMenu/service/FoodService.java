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
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Transactional
    public FoodResponseDTO insert(FoodRequestDTO data) {
        Food food = new Food(data);
        Food create = foodRepository.save(food);
        return new FoodResponseDTO(create);
    }

    @Transactional(readOnly = true)
    public List<FoodResponseDTO> findAll() {
        return foodRepository.findAll().stream()
                .map(FoodResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public FoodResponseDTO findById(Long id) {
        Food result = foodRepository.findById(id).get();
        return new FoodResponseDTO(result);
    }

    @Transactional
    public FoodResponseDTO update(Long id, FoodRequestDTO data) {
        Food entity = foodRepository.getReferenceById(id);
        updateData(entity, data);
        Food updated = foodRepository.save(entity);
        return new FoodResponseDTO(updated);
    }

    private void updateData(Food entity, FoodRequestDTO data) {
        entity.setTitle(data.title());
        entity.setPrice(data.price());
        entity.setImgUri(data.imgUri());
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public void delete(Long id) {
        foodRepository.deleteById(id);
    }
}
package com.souza.charles.digitalMenu.service;
/*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: February 10, 2025
 */

import com.souza.charles.digitalMenu.dto.FoodRequestDTO;
import com.souza.charles.digitalMenu.dto.FoodResponseDTO;
import com.souza.charles.digitalMenu.entities.Food;
import com.souza.charles.digitalMenu.repository.FoodRepository;
import com.souza.charles.digitalMenu.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Transactional
    public FoodResponseDTO insert(FoodRequestDTO data) {
        if (data.title() == null || data.title().isEmpty() ||
                data.price() == null || data.price() < 0 ||
                data.image() == null || data.image().isEmpty()) {
            throw new InvalidDataException();
        }
        foodRepository.findByImage(data.image())
                .ifPresent(food -> {
                    throw new DuplicateImageException(data.image());
                });
        Food entity = new Food(data);
        Food create = foodRepository.save(entity);
        return new FoodResponseDTO(create);
    }

    @Transactional(readOnly = true)
    public List<FoodResponseDTO> findAll() {
        try {
            List<Food> list = foodRepository.findAll();
            return list.stream()
                    .map(FoodResponseDTO::new)
                    .toList();
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new EmptyTableException();
        }
    }

    @Transactional(readOnly = true)
    public FoodResponseDTO findById(Long id) {
        Food entity = foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return new FoodResponseDTO(entity);
    }

    @Transactional
    public FoodResponseDTO update(Long id, FoodRequestDTO data) {
        Food entity = foodRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        foodRepository.findByImage(data.image())
                .filter(existingFood -> !existingFood.getId().equals(id))
                .ifPresent(food -> {
                    throw new DuplicateImageException(data.image());
                });
        updateData(entity, data);
        Food updatedUser = foodRepository.save(entity);
        return new FoodResponseDTO(updatedUser);
    }

    private void updateData(Food entity, FoodRequestDTO data) {
        if (data.title() == null || data.title().isEmpty() ||
                data.price() == null || data.price() < 0 ||
                data.image() == null || data.image().isEmpty()) {
            throw new InvalidDataException();
        }
        entity.setTitle(data.title());
        entity.setPrice(data.price());
        entity.setImage(data.image());
    }

    @Transactional
    public void delete(Long id) {
        try {
            foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
            foodRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
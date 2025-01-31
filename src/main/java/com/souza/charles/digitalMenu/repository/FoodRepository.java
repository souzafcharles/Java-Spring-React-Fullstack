package com.souza.charles.digitalMenu.repository;

import com.souza.charles.digitalMenu.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}

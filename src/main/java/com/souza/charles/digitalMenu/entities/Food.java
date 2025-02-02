package com.souza.charles.digitalMenu.entities;
 /*
 Tutorial title: Building a Full-Stack Application with Java Spring and React
 Instructor: Fernanda Kipper - kipperDev
 Project adapted by: Charles Fernandes de Souza
 Date: January 31, 2025
 */

import com.souza.charles.digitalMenu.dto.FoodRequestDTO;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "tb_foods")
public class Food implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double price;
    @Column(name = "imguri")
    private String imgUri;

    public Food() {
    }

    public Food(Long id, String title, Double price, String imgUri) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.imgUri = imgUri;
    }

    public Food(FoodRequestDTO data) {
        this.title = data.title();
        this.price = data.price();
        this.imgUri = data.imgUri();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
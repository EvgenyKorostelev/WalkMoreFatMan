package ru.korostelev.WalkMoreFatMan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


@Entity
@AllArgsConstructor
@Data
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dishName;

    private Integer caloriesPerServing;

    private Integer proteins;

    private Integer fats;

    private Integer carbohydrates;

}

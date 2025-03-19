package ru.korostelev.WalkMoreFatMan.entity;

import jakarta.persistence.Entity;
import ru.korostelev.WalkMoreFatMan.entity.dto.BJU;

import java.util.UUID;

@Entity
public class Dish {

    private UUID dishId;

    private String dishName;

    private Integer caloriesPerServing;

    private Integer Proteins;

    private Integer Fats;

    private Integer Carbohydrates;

}

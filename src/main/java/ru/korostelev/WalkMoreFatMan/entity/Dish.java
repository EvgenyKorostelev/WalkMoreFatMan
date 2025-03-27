package ru.korostelev.WalkMoreFatMan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "t_bju_dish", schema = "diet_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_dish_name")
    private String name;

    @Column(name = "c_calories_per_serving")
    private Integer caloriesPerServing;

    @Column(name = "c_proteins")
    private Integer proteins;

    @Column(name = "c_fats")
    private Integer fats;

    @Column(name = "c_carbohydrates")
    private Integer carbohydrates;

}

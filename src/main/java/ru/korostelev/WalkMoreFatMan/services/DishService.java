package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewDishPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateDishPayload;
import ru.korostelev.WalkMoreFatMan.entity.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {

    Optional<Dish> createDish(NewDishPayload payload);

    List<Dish> findAllDishes();

    Optional<Dish>  findDishByName(String dishName);

    Optional<Dish>  updateDish(String dishName, UpdateDishPayload payload);

    void deleteDishByName(String dishName);
}

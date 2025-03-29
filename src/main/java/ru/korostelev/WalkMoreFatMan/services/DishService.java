package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewDishPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateDishPayload;
import ru.korostelev.WalkMoreFatMan.entity.Dish;

import java.util.List;

public interface DishService {

    Dish addDish(NewDishPayload payload);

    List<Dish> findAllDishes();

    Dish findDishByName(String dishName);

    Dish updateDish(String dishName, UpdateDishPayload payload);

    void deleteDishByName(String dishName);
}

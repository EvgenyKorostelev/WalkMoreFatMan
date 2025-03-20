package ru.korostelev.WalkMoreFatMan.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewDishPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateDishPayload;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.repository.DishRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishServiceImp implements DishService {

    private final DishRepository dishRepository;


    @Override
    public Dish addDish(NewDishPayload payload) {
        return dishRepository.save(
                new Dish(null, payload.dishName(), payload.caloriesPerServing(),
                        payload.proteins(), payload.fats(), payload.carbohydrates()));
    }

    @Override
    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findDishByName(String dishName) {
        return dishRepository.findByName(dishName).orElseThrow(() -> new NoSuchElementException("dish.not_found"));
    }

    @Override
    public Dish updateDish(String dishName, UpdateDishPayload payload) {
        Optional<Dish> dish = dishRepository.findByName(dishName);
        return dish.map(value -> dishRepository.save(
                        new Dish(dish.get().getId(), payload.dishName(), payload.caloriesPerServing(),
                                payload.proteins(), payload.fats(), payload.carbohydrates())))
                .orElse(null);
    }

    @Override
    public void deleteDishByName(String dishName) {
        dishRepository.deleteByName(dishName);
    }
}

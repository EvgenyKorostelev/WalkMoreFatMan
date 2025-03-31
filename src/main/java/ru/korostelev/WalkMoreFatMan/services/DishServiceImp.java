package ru.korostelev.WalkMoreFatMan.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewDishPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateDishPayload;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.entity.User;
import ru.korostelev.WalkMoreFatMan.repository.DishRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DishServiceImp implements DishService {

    private final DishRepository dishRepository;


    @Override
    public Optional<Dish> createDish(NewDishPayload payload) {

        if (dishRepository.findByName(payload.name()).isPresent()) {
            return Optional.empty();
        } else {
            return Optional.of(dishRepository.save(
                    new Dish(null, payload.name(), payload.caloriesPerServing(),
                            payload.proteins(), payload.fats(), payload.carbohydrates())));
        }
    }

    @Override
    public List<Dish> findAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> findDishByName(String dishName) {
        return dishRepository.findByName(dishName);
    }

    @Override
    public  Optional<Dish> updateDish(String dishName, UpdateDishPayload payload) {
        Optional<Dish> dish = dishRepository.findByName(dishName);
        return dish.map(value -> dishRepository.save(
                        new Dish(dish.get().getId(), payload.name(), payload.caloriesPerServing(),
                                payload.proteins(), payload.fats(), payload.carbohydrates())));
    }

    @Override
    public void deleteDishByName(String dishName) {
        dishRepository.deleteByName(dishName);
    }
}

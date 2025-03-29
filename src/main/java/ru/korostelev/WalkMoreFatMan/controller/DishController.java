package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.WalkMoreFatMan.controller.payload.DishNamePayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewDishPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateDishPayload;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.services.DishService;

import java.util.List;

@RestController
@RequestMapping("/WalkMoreFatMan-api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    public Dish registeredDish(@RequestBody @Valid NewDishPayload payload) {
        return dishService.addDish(payload);
    }

    @GetMapping
    public List<Dish> allDishes() {
        return dishService.findAllDishes();
    }

    @GetMapping("/{dishName:\\S+}")
    public Dish findDish(@PathVariable @Valid DishNamePayload dishNamePayload) {
        return dishService.findDishByName(dishNamePayload.name());
    }

    @PutMapping("/{dishName:\\S+}")
    public Dish updateDish(@PathVariable @Valid DishNamePayload dishNamePayload,
                           @RequestBody @Valid UpdateDishPayload payload) {
        return dishService.updateDish(dishNamePayload.name(), payload);
    }

    @DeleteMapping("/{dishName:\\S+}")
    @Transactional
    public void deleteDish(@PathVariable @Valid DishNamePayload dishNamePayload) {
        dishService.deleteDishByName(dishNamePayload.name());
    }
}

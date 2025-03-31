package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.korostelev.WalkMoreFatMan.controller.exceptions.AlreadyExistException;
import ru.korostelev.WalkMoreFatMan.controller.exceptions.NotRegisteredException;
import ru.korostelev.WalkMoreFatMan.controller.payload.DishNamePayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.NewDishPayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UpdateDishPayload;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.services.DishService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/WalkMoreFatMan-api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping
    public ResponseEntity<?> registeredDish(@RequestBody @Valid NewDishPayload payload,
                                            BindingResult bindingResult,
                                            UriComponentsBuilder uriComponentsBuilder)
            throws BindException, AlreadyExistException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Optional<Dish> dish = dishService.createDish(payload);
            if (dish.isPresent()) {
                return ResponseEntity
                        .created(uriComponentsBuilder
                                .replacePath("/WalkMoreFatMan-api/dishes/{dishName}")
                                .build(Map.of("dishName", dish.get().getName())))
                        .body(dish);
            } else {
                throw new AlreadyExistException("walk_more_fat_man.dish.create.errors.already_registered");
            }
        }
    }

    @GetMapping
    public List<Dish> allDishes() {
        return dishService.findAllDishes();
    }

    @GetMapping("/{dishName:\\S+}")
    public Dish findDish(@PathVariable @Valid DishNamePayload dishNamePayload) {
        return dishService.findDishByName(dishNamePayload.name()).orElseThrow(
                () -> new NoSuchElementException("walk_more_fat_man.dish.errors.not_found"));
    }

    @PutMapping("/{dishName:\\S+}")
    public ResponseEntity<?> updateDish(@PathVariable @Valid DishNamePayload dishNamePayload,
                                        @RequestBody @Valid UpdateDishPayload payload,
                                        BindingResult bindingResult,
                                        UriComponentsBuilder uriComponentsBuilder)
            throws BindException, NotRegisteredException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Optional<Dish> dish = dishService.updateDish(dishNamePayload.name(), payload);
            if (dish.isPresent()) {
                return ResponseEntity
                        .created(uriComponentsBuilder
                                .replacePath("/WalkMoreFatMan-api/dishes/{dishName}")
                                .build(Map.of("userName", dish.get().getName())))
                        .body(dish);
            } else {
                throw new NotRegisteredException("walk_more_fat_man.dish.update.errors.not_registered");
            }
        }
    }

    @DeleteMapping("/{dishName:\\S+}")
    @Transactional
    public ResponseEntity<?> deleteDish(@PathVariable @Valid DishNamePayload dishNamePayload) {
        dishService.deleteDishByName(dishNamePayload.name());
        return ResponseEntity.accepted().build();
    }
}

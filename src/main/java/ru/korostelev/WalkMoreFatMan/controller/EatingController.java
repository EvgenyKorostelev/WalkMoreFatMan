package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.services.EatingService;

import java.util.List;

@RestController
@RequestMapping("/WalkMoreFatMan-api/eating")
@RequiredArgsConstructor
public class EatingController {

    private final EatingService eatingService;

    @PostMapping
    public void eating(@PathVariable @Valid String userName,
                       @RequestBody List<Dish> dishesList) {
        eatingService.eating(userName, dishesList);
    }
}

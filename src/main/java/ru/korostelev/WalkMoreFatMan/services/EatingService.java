package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;
import ru.korostelev.WalkMoreFatMan.entity.Dish;

import java.util.List;

public interface EatingService {

    void eating(@Valid String userName, List<String> dishesList);
}

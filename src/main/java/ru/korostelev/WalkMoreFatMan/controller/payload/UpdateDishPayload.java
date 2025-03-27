package ru.korostelev.WalkMoreFatMan.controller.payload;

public record UpdateDishPayload(

        String name,

        Integer caloriesPerServing,

        Integer proteins,

        Integer fats,

        Integer carbohydrates) {
}

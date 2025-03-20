package ru.korostelev.WalkMoreFatMan.controller.payload;

public record UpdateDishPayload(

        String dishName,

        Integer caloriesPerServing,

        Integer proteins,

        Integer fats,

        Integer carbohydrates) {
}

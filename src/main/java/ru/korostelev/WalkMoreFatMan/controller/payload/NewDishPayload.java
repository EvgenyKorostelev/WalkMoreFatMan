package ru.korostelev.WalkMoreFatMan.controller.payload;

public record NewDishPayload(

        String name,

        Integer caloriesPerServing,

        Integer proteins,

        Integer fats,

        Integer carbohydrates) {
}

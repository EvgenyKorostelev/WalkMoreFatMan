package ru.korostelev.WalkMoreFatMan.controller.payload;

public record NewDishPayload(

                            String dishName,

                            Integer caloriesPerServing,

                            Integer proteins,

                            Integer fats,

                            Integer carbohydrates) {
}

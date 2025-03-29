package ru.korostelev.WalkMoreFatMan.controller.payload;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDishPayload(

        @NotNull(message = "{walk_more_fat_man.dish.update.errors.name_size_is_null}")
        @Size(min = 3, max = 50, message = "{walk_more_fat_man.dish.update.errors.name_size_is_invalid}")
        String name,

        @NotNull(message = "{walk_more_fat_man.dish.update.errors.caloriesPerServing_size_is_null}")
        @Min(value = 1, message = "{walk_more_fat_man.dish.update.errors.caloriesPerServing_size_is_invalid}")
        @Max(value = 10000, message = "{walk_more_fat_man.dish.update.errors.caloriesPerServing_size_is_invalid}")
        Integer caloriesPerServing,

        @NotNull(message = "{walk_more_fat_man.dish.update.errors.proteins_size_is_null}")
        @Min(value = 0, message = "{walk_more_fat_man.dish.update.errors.proteins_size_is_invalid}")
        @Max(value = 100, message = "{walk_more_fat_man.dish.update.errors.proteins_size_is_invalid}")
        Integer proteins,

        @NotNull(message = "{walk_more_fat_man.dish.update.errors.fats_size_is_null}")
        @Min(value = 0, message = "{walk_more_fat_man.dish.update.errors.fats_size_is_invalid}")
        @Max(value = 100, message = "{walk_more_fat_man.dish.update.errors.fats_size_is_invalid}")
        Integer fats,

        @NotNull(message = "{walk_more_fat_man.dish.update.errors.carbohydrates_size_is_null}")
        @Min(value = 0, message = "{walk_more_fat_man.dish.update.errors.carbohydrates_size_is_invalid}")
        @Max(value = 100, message = "{walk_more_fat_man.dish.update.errors.carbohydrates_size_is_invalid}")
        Integer carbohydrates) {
}

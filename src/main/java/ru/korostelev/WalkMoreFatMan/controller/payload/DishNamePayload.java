package ru.korostelev.WalkMoreFatMan.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DishNamePayload(

        @NotNull(message = "{walk_more_fat_man.dish.errors.name_size_is_null}")
        @Size(min = 3, max = 50, message = "{walk_more_fat_man.dish.errors.name_size_is_invalid}")
        String name
) {
}

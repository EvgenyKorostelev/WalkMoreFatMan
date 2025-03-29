package ru.korostelev.WalkMoreFatMan.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatePayload(
        @NotNull(message = "{walk_more_fat_man.date.errors.size_is_null}")
        @Size(min = 10, max = 10, message = "{walk_more_fat_man.date.errors.size_is_invalid}")
        String date
) {
}

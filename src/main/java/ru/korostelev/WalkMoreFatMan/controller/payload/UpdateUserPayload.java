package ru.korostelev.WalkMoreFatMan.controller.payload;

import jakarta.validation.constraints.*;
import ru.korostelev.WalkMoreFatMan.entity.enums.Gender;
import ru.korostelev.WalkMoreFatMan.entity.enums.Target;

public record UpdateUserPayload(

        @NotNull(message = "{walk_more_fat_man.user.update.errors.name_size_is_null}")
        @Size(min = 3, max = 50, message = "{walk_more_fat_man.user.update.errors.name_size_is_invalid}")
        String name,

        @NotNull(message = "{walk_more_fat_man.user.update.errors.gender_size_is_null}")
        Gender gender,

        @NotNull(message = "{walk_more_fat_man.user.update.errors.email_size_is_null}")
        @Size(min = 5, max = 50, message = "{walk_more_fat_man.user.update.errors.email_size_is_invalid}")
        @Email
        String email,

        @NotNull(message = "{walk_more_fat_man.user.update.errors.age_size_is_null}")
        @Min(value = 1, message = "{walk_more_fat_man.user.update.errors.age_size_is_invalid}")
        @Max(value = 120, message = "{walk_more_fat_man.user.update.errors.age_size_is_invalid}")
        Integer age,

        @NotNull(message = "{walk_more_fat_man.user.update.errors.weight_size_is_null}")
        @Min(value = 1, message = "{walk_more_fat_man.user.update.errors.weight_size_is_invalid}")
        @Max(value = 299, message = "{walk_more_fat_man.user.update.errors.weight_size_is_invalid}")
        Integer weight,

        @NotNull(message = "{walk_more_fat_man.user.update.errors.height_size_is_null}")
        @Min(value = 81, message = "{walk_more_fat_man.user.update.errors.height_size_is_invalid}")
        @Max(value = 249, message = "{walk_more_fat_man.user.update.errors.height_size_is_invalid}")
        Integer height,

        @NotNull(message = "{walk_more_fat_man.user.update.errors.target_size_is_null}")
        Target target
) {
}

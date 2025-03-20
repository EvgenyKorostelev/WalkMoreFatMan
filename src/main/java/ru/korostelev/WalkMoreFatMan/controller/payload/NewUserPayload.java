package ru.korostelev.WalkMoreFatMan.controller.payload;

import ru.korostelev.WalkMoreFatMan.entity.dto.Gender;
import ru.korostelev.WalkMoreFatMan.entity.dto.Target;

public record NewUserPayload(

        String name,

        Gender gender,

        String email,

        Integer age,

        Integer weight,

        Integer height,

        Target target) {
}

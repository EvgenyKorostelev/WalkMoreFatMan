package ru.korostelev.WalkMoreFatMan.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {

    @JsonProperty("male")
    MALE("мужской"),

    @JsonProperty("female")
    FEMALE("женский");

    Gender(String genderDescription) {
    }
}


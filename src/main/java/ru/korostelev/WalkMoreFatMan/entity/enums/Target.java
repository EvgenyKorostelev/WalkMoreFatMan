package ru.korostelev.WalkMoreFatMan.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Target {

    @JsonProperty("weight_loss")
    WEIGHT_LOSS("Похудение"),

    @JsonProperty("maintenance")
    MAINTENANCE("Поддержание"),

    @JsonProperty("gain")
    GAIN("Набор");

    Target(String targetDescription) {

    }
}

package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;
import ru.korostelev.WalkMoreFatMan.controller.payload.UserNamePayload;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface CalorieCheckService {

    boolean checkUserCalorie(UserNamePayload namePayload, LocalDate date);

}

package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface CalorieCheckService {

    boolean checkUserCalorie(@Valid String userName, LocalDate date);

}

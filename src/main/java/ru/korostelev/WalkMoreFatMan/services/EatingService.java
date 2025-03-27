package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public interface EatingService {

    void eating(@Valid String userName, List<String> dishesList);

    List<EatingReport> fullReportsAllUsers();

    EatingReport fullUserReports(@Valid String userName);

    List<EatingReport> dailyAllUsersReports(LocalDate date);

    EatingReport dailyUserReport(@Valid String userName, LocalDate date);
}

package ru.korostelev.WalkMoreFatMan.services;

import jakarta.validation.Valid;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EatingService {

    Optional<EatingReport> eating(String userName, List<String> dishesList);

    List<EatingReport> fullReportsAllUsers();

    Optional<EatingReport> fullUserReports(String userName);

    List<EatingReport> dailyAllUsersReports(LocalDate date);

    Optional<EatingReport> dailyUserReport(String userName, LocalDate date);
}

package ru.korostelev.WalkMoreFatMan.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;
import ru.korostelev.WalkMoreFatMan.services.CalorieCheckService;
import ru.korostelev.WalkMoreFatMan.services.EatingService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/WalkMoreFatMan-api/eating")
@RequiredArgsConstructor
public class EatingController {

    private final EatingService eatingService;

    private final CalorieCheckService checkService;

    @PostMapping("/{userName:\\S+}")
    public void eating(@PathVariable @Valid String userName,
                       @RequestBody @Valid List<String> dishes) {
        eatingService.eating(userName, dishes);
    }

    @GetMapping
    public List<EatingReport> fullReports() {
        return eatingService.fullReportsAllUsers();
    }

    @GetMapping("/reports/{userName:\\S+}")
    public EatingReport fullReportsUser(@PathVariable @Valid String userName) {
        return eatingService.fullUserReports(userName);
    }

    @GetMapping("/reports/{date:\\S+}")
    public List<EatingReport> dailyReports(@PathVariable @Valid String date) {
        return eatingService.dailyAllUsersReports(dateParser(date));
    }

    @GetMapping("/reports/{userName:\\S+}/{date:\\S+}")
    public EatingReport dailyReport(@PathVariable @Valid String userName,
                                    @PathVariable @Valid String date) {
        return eatingService.dailyUserReport(userName, dateParser(date));
    }

    @GetMapping("/reports/calorie_check/{userName:\\S+}/{date:\\S+}")
    public boolean calorieCheck(@PathVariable @Valid String userName,
                                @PathVariable @Valid String date) {
        return checkService.checkUserCalorie(
                userName, dateParser(date));
    }

    private LocalDate dateParser(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}

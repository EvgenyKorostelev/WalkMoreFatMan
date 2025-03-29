package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.WalkMoreFatMan.controller.payload.DatePayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UserNamePayload;
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

    private final CalorieCheckService calorieCheckService;

    @PostMapping("/{userName:\\S+}")
    public void eating(@PathVariable @Valid UserNamePayload userNamePayload,
                       @RequestBody List<String> dishes) {
        eatingService.eating(userNamePayload.name(), dishes);
    }

    @GetMapping
    public List<EatingReport> fullReports() {
        return eatingService.fullReportsAllUsers();
    }

    @GetMapping("/reports/{userName:\\S+}")
    public EatingReport fullReportsUser(@PathVariable @Valid UserNamePayload userNamePayload) {
        return eatingService.fullUserReports(userNamePayload.name());
    }

    @GetMapping("/reports/{date:\\S+}")
    public List<EatingReport> dailyReports(@PathVariable @Valid DatePayload date) {
        return eatingService.dailyAllUsersReports(dateParser(date));
    }

    @GetMapping("/reports/{userName:\\S+}/{date:\\S+}")
    public EatingReport dailyReport(@PathVariable @Valid UserNamePayload userNamePayload,
                                    @PathVariable @Valid DatePayload datePayload) {
        return eatingService.dailyUserReport(userNamePayload.name(), dateParser(datePayload));
    }

    @GetMapping("/reports/calorie_check/{userName:\\S+}/{date:\\S+}")
    public boolean calorieCheck(@PathVariable @Valid UserNamePayload userName,
                                @PathVariable @Valid DatePayload datePayload) {
        return calorieCheckService.checkUserCalorie(userName, dateParser(datePayload));
    }

    private LocalDate dateParser(DatePayload date) {
        return LocalDate.parse(date.date(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}

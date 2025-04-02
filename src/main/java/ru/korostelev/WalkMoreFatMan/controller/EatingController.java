package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.korostelev.WalkMoreFatMan.controller.payload.DatePayload;
import ru.korostelev.WalkMoreFatMan.controller.payload.UserNamePayload;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;
import ru.korostelev.WalkMoreFatMan.services.CalorieCheckService;
import ru.korostelev.WalkMoreFatMan.services.EatingService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/WalkMoreFatMan-api/eating")
@RequiredArgsConstructor
public class EatingController {

    private final EatingService eatingService;

    private final CalorieCheckService calorieCheckService;

    @PostMapping("/{userName:\\S+}")
    public ResponseEntity<?> eating(@PathVariable @Valid UserNamePayload userNamePayload,
                                    @RequestBody List<String> dishes,
                                    BindingResult bindingResult,
                                    UriComponentsBuilder uriComponentsBuilder) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Optional<EatingReport> eatingReport = eatingService.eating(userNamePayload.name(), dishes);
            if (eatingReport.isPresent()) {
                return ResponseEntity.accepted().body(eatingReport);
            } else {
                return ResponseEntity.ofNullable(eatingReport);
            }
        }
    }

    @GetMapping
    public List<EatingReport> fullReports() {
        return eatingService.fullReportsAllUsers();
    }

    @GetMapping("/reports/{userName:\\S+}")
    public EatingReport fullReportsUser(@PathVariable @Valid UserNamePayload userNamePayload) {
        return eatingService.fullUserReports(userNamePayload.name()).orElseThrow(
                () -> new NoSuchElementException("walk_more_fat_man.eating_report.errors.not_found"));
    }

    @GetMapping("/reports/{date:\\S+}")
    public List<EatingReport> dailyReports(@PathVariable @Valid DatePayload date) {
        return eatingService.dailyAllUsersReports(dateParser(date));
    }

    @GetMapping("/reports/{userName:\\S+}/{date:\\S+}")
    public EatingReport dailyReport(@PathVariable @Valid UserNamePayload userNamePayload,
                                    @PathVariable @Valid DatePayload datePayload) {
        return eatingService.dailyUserReport(userNamePayload.name(), dateParser(datePayload))
                .orElseThrow(
                        () -> new NoSuchElementException("walk_more_fat_man.eating_report.errors.not_found"));
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

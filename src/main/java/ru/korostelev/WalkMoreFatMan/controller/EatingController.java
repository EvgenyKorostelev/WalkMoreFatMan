package ru.korostelev.WalkMoreFatMan.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;
import ru.korostelev.WalkMoreFatMan.services.CalorieCheckService;
import ru.korostelev.WalkMoreFatMan.services.EatingService;

import java.util.List;

@RestController
@RequestMapping("/WalkMoreFatMan-api/eating")
@RequiredArgsConstructor
public class EatingController {

    private final EatingService eatingService;

    private final CalorieCheckService checkService;

    @PostMapping
    public void eating(@PathVariable @Valid String userName,
                       @RequestBody @Valid List<String> dishesList) {
        eatingService.eating(userName, dishesList);
    }

    @GetMapping
    public List<EatingReport> fullReports(){
        return eatingService.fullReportsAllUsers();
    }

    @GetMapping("/reports/{userName:\\s+}")
    public EatingReport fullReportsUser(@PathVariable @Valid String userName){
        return eatingService.fullUserReports(userName);
    }

    @GetMapping("/reports/day")
    public List<EatingReport> dailyReports(){
        return eatingService.dailyAllUsersReports();
    }

    @GetMapping("/reports/day/{userName:\\s+}")
    public EatingReport dailyReport(@PathVariable @Valid String userName){
        return eatingService.dailyUserReport(userName);
    }

    @GetMapping("/reports/calorie_check/{userName:\\s+}")
    public boolean calorieCheck (@PathVariable @Valid String userName){
        return checkService.checkUserCalorie(userName);
    }
}

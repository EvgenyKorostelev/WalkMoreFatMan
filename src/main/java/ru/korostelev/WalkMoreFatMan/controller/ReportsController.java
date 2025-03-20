//package ru.korostelev.WalkMoreFatMan.controller;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.korostelev.WalkMoreFatMan.entity.EatingReport;
//import ru.korostelev.WalkMoreFatMan.services.CalorieCheckService;
//import ru.korostelev.WalkMoreFatMan.services.EatingService;
//import ru.korostelev.WalkMoreFatMan.services.ReportService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/WalkMoreFatMan-api/reports")
//@RequiredArgsConstructor
//public class ReportsController {
//
//    private final EatingService eatingService;
//
//    private final ReportService reportService;
//
//    private final CalorieCheckService checkService;
//
//
//    @GetMapping
//    public List<EatingReport> fullReports(){
//        return reportService.fullReportsAllUsers();
//    }
//
//    @GetMapping("/{userName:\\s+}")
//    public EatingReport fullReportsUser(@PathVariable @Valid String userName){
//        return reportService.fullUserReports(userName);
//    }
//
//    @GetMapping("/day")
//    public List<EatingReport> dailyReports(){
//        return reportService.dailyAllUsersReports();
//    }
//
//    @GetMapping("/day/{userName:\\s+}")
//    public EatingReport dailyReport(@PathVariable @Valid String userName){
//        return reportService.dailyUserReport(userName);
//    }
//
//    @GetMapping("/calorie_check/{userName:\\s+}")
//    public boolean calorieCheck (@PathVariable @Valid String userName){
//        return checkService.checkUserCalorie(userName);
//    }
//}

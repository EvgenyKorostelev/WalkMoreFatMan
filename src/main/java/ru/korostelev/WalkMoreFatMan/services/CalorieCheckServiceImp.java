package ru.korostelev.WalkMoreFatMan.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.WalkMoreFatMan.entity.Dish;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;
import ru.korostelev.WalkMoreFatMan.entity.User;
import ru.korostelev.WalkMoreFatMan.entity.dto.Target;
import ru.korostelev.WalkMoreFatMan.repository.DishRepository;
import ru.korostelev.WalkMoreFatMan.repository.EatingHistoryRepository;
import ru.korostelev.WalkMoreFatMan.repository.UserRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CalorieCheckServiceImp implements CalorieCheckService {

    private final EatingHistoryRepository eatingHistoryRepository;

    private final UserRepository userRepository;

    private final DishRepository dishRepository;

    @Override
    public boolean checkUserCalorie(String userName, LocalDate date) {

        User user = userRepository.findByName(userName).orElseThrow(
                () -> new NoSuchElementException("user.not_found"));

        EatingReport eatingUserDaily = eatingHistoryRepository.findByUserIdAndDate(user.getId(),
                date);

        List<Dish> dishList = findDishes(eatingUserDaily);

        Integer calories = caloriesCalculation(dishList);


        if (user.getTarget().equals(Target.MAINTENANCE)) {
            return user.getBmr().equals(calories);
        } else if (user.getTarget().equals(Target.GAIN)) {
            return user.getBmr() < calories;
        } else if (user.getTarget().equals(Target.WEIGHT_LOSS)) {
            return user.getBmr() > calories;
        } else {
            return false;
        }

    }

    private List<Dish> findDishes(EatingReport eatingReport){
        return Arrays.stream(eatingReport.getDishesId()
                        .substring(1, eatingReport.getDishesId().length() - 1)
                        .replaceAll(" ", "").split(",")).toList()
                .stream()
                .map(o -> dishRepository.findById(Integer.valueOf(o)).orElseThrow(
                        () -> new NoSuchElementException("dish.not_found")))
                .toList();
    }

    private Integer caloriesCalculation(List<Dish> dishes){
        return dishes.stream()
                .map(Dish::getCaloriesPerServing)
                .toList().stream()
                .reduce(0, Integer::sum);
    }
}

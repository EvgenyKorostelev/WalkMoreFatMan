package ru.korostelev.WalkMoreFatMan.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;
import ru.korostelev.WalkMoreFatMan.repository.DishRepository;
import ru.korostelev.WalkMoreFatMan.repository.EatingHistoryRepository;
import ru.korostelev.WalkMoreFatMan.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EatingServiceImp implements EatingService {

    private final EatingHistoryRepository eatingHistoryRepository;

    private final UserRepository userRepository;

    private final DishRepository dishRepository;

    @Override
    public Optional<EatingReport> eating(String userName, List<String> dishesList) {
        return Optional.of(eatingHistoryRepository.save(
                new EatingReport(null, LocalDate.now(),
                        userRepository.findByName(userName).orElseThrow(
                                () -> new NoSuchElementException("user.not_found")).getId(),
                        dishesList.stream()
                                .map(o -> dishRepository.findByName(o).orElseThrow(
                                        () -> new NoSuchElementException("dish.not_found")).getId())
                                .toList().toString())));
    }

    @Override
    public List<EatingReport> fullReportsAllUsers() {
        return eatingHistoryRepository.findAll();
    }

    @Override
    public Optional<EatingReport> fullUserReports(String userName) {
        Integer userId = findUserIdByUserName(userName);
        return Optional.of(eatingHistoryRepository.findAllByUserId(userId));
    }

    @Override
    public List<EatingReport> dailyAllUsersReports(LocalDate date) {
        return eatingHistoryRepository.findByDate(date);
    }

    @Override
    public Optional<EatingReport> dailyUserReport(String userName, LocalDate date) {
        Integer userId = findUserIdByUserName(userName);
        return Optional.of(eatingHistoryRepository.findByUserIdAndDate(userId, date));
    }

    private Integer findUserIdByUserName(String userName) {
        return userRepository.findByName(userName).orElseThrow(
                () -> new NoSuchElementException("user.not_found")
        ).getId();
    }
}

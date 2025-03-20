package ru.korostelev.WalkMoreFatMan.services;

import lombok.RequiredArgsConstructor;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;
import ru.korostelev.WalkMoreFatMan.repository.DishRepository;
import ru.korostelev.WalkMoreFatMan.repository.EatingHistoryRepository;
import ru.korostelev.WalkMoreFatMan.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class EatingServiceImp implements EatingService {

    private final EatingHistoryRepository eatingHistoryRepository;

    private final UserRepository userRepository;

    private final DishRepository dishRepository;

    @Override
    public void eating(String userName, List<String> dishesList) {
        eatingHistoryRepository.save(
                new EatingReport(null, null,
                        userRepository.findByName(userName).orElseThrow(
                                () -> new NoSuchElementException("user.not_found")).getId(),
                        dishesList.stream()
                                .map(o -> dishRepository.findByName(o).orElseThrow(
                                        () -> new NoSuchElementException("dish.not_found")).getId())
                                .toList()));
    }
}

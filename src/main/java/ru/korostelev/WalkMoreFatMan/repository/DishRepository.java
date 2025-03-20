package ru.korostelev.WalkMoreFatMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korostelev.WalkMoreFatMan.entity.Dish;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    Optional<Dish> findByName(String dishName);

    void deleteByName(String dishName);
}

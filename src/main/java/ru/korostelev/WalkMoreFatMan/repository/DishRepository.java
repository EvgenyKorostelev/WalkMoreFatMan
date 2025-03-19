package ru.korostelev.WalkMoreFatMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korostelev.WalkMoreFatMan.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Integer> {
}

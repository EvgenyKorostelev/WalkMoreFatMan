package ru.korostelev.WalkMoreFatMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;

public interface EatingHistoryRepository extends JpaRepository<EatingReport, Integer> {
}

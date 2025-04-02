package ru.korostelev.WalkMoreFatMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.korostelev.WalkMoreFatMan.entity.EatingReport;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EatingHistoryRepository extends JpaRepository<EatingReport, Integer> {

    EatingReport findAllByUserId(Integer userId);

    List<EatingReport> findByDate(LocalDate date);

    EatingReport findByUserIdAndDate(Integer userId, LocalDate date);
}

package ru.korostelev.WalkMoreFatMan.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class EatingReport {

    private Integer id;

    private String date;

    private Integer userId;

    private List<Integer> dishesId;
}

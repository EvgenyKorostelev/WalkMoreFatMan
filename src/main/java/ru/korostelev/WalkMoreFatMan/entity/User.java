package ru.korostelev.WalkMoreFatMan.entity;

import jakarta.persistence.Entity;
import ru.korostelev.WalkMoreFatMan.entity.dto.Gender;
import ru.korostelev.WalkMoreFatMan.entity.dto.Target;

import java.util.UUID;

@Entity
public class User {

    private UUID userId;

    private String name;

    private Gender gender;

    private String email;

    private Integer age;

    private Integer weight;

    private Integer height;

    private Target target;

    private Double bmr;

    public User(UUID userId, String name, Gender gender, String email, Integer age, Integer weight,
                Integer height, Target target) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.target = target;
        this.bmr = bmrCreator(this.weight, this.height, this.age, this.gender);
    }

    private Double bmrCreator(Integer weight, Integer height, Integer age, Gender gender){
        if(gender.equals(Gender.MALE)){
            return (13.7516 * weight) + (5.0033 * height) - (6.755 * age) + 66.473;
        } else {
            return (9.5634 * weight) + (1.8496 * height) - (4.6756 * age) + 655.0955;
        }
    }
}

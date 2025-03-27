package ru.korostelev.WalkMoreFatMan.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.korostelev.WalkMoreFatMan.entity.dto.Gender;
import ru.korostelev.WalkMoreFatMan.entity.dto.Target;

@Entity
@Table(name = "t_bju_user", schema = "diet_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_user_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_gender")
    private Gender gender;

    @Email
    @Column(name = "c_email")
    private String email;

    @Column(name = "c_age")
    private Integer age;

    @Column(name = "c_weight")
    private Integer weight;

    @Column(name = "c_height")
    private Integer height;

    @Enumerated(EnumType.STRING)
    @Column(name = "c_target")
    private Target target;

    @Column(name = "c_bmr")
    private Integer bmr;

    public User(Integer id, String name, Gender gender, String email, Integer age,
                Integer weight, Integer height, Target target) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.target = target;
        this.bmr = bmrCreator(this.weight, this.height, this.age, this.gender);
    }

    private Integer bmrCreator(Integer weight, Integer height, Integer age, Gender gender) {
        if (gender.equals(Gender.MALE)) {
            return (int) ((13.7516 * weight) + (5.0033 * height) - (6.755 * age) + 66.473);
        } else {
            return (int) ((9.5634 * weight) + (1.8496 * height) - (4.6756 * age) + 655.0955);
        }
    }
}

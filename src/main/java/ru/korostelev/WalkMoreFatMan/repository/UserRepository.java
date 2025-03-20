package ru.korostelev.WalkMoreFatMan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.korostelev.WalkMoreFatMan.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByName(String userName);

    void deleteByName(String userName);
}
